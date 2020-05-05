package dev.saksmt.blog.deploy

import java.io.{BufferedReader, InputStreamReader}
import java.nio.charset.StandardCharsets

import io.circe.optics.JsonPath
import io.circe.Json
import io.circe.yaml.parser
import io.circe.yaml.syntax._
import io.circe.syntax._

// todo: this object desperately needs refactoring...
// todo: interaction with k8s to obtain current routing
// todo: CLI with deploy, switch, stop
// todo: github action triggered by event (event originates in kuber with call to webservice with basic auth) +
//       artifact with html page with links to urls for trigger of deployment
// todo: much later: web service + gradient switch (canary-like) with auto-rollbacks based on logs/alerts
object DeployApp extends App {
  val (version, color) = args.toList match {
    case v :: c :: Nil if Seq("blue", "green").contains(c.toLowerCase) => (v, Color.from(c))
    case Nil =>
      System.err.println("Usage: cat kuber-resource | deploy <version> <color-to-deploy-to>")
      sys.exit(1)
  }

  parser
    .parseDocuments(new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8)))
    .flatMap {
      case Left(e)    => e.printStackTrace(System.err); None
      case Right(doc) => Some(doc)
    }
    .map(authorizeDocker)
    .map(paint(_, Color.Blue))
    .map(setupStageRouting(_, Color.Blue))
    .foreach { doc =>
      println("---")
      println(doc.asYaml.spaces2)
    }

  sealed trait Color extends Product {
    override def toString: String = productPrefix.toLowerCase()
    def switch: Color
  }
  object Color {
    def from(string: String): Color = if (string.toLowerCase.trim == "blue") Blue else Green

    final case object Blue extends Color {
      override def switch: Color = Green
    }
    final case object Green extends Color {
      override def switch: Color = Blue
    }
  }

  def $ = JsonPath.root

  def setupVersion(kubeResource: Json, version: String): Json =
    if ($.kind.string.getOption(kubeResource).map(_.toLowerCase).contains("deployment")) {
      $.spec.template.spec.containers.each.image.string
        .modify(_.replace("$$VERSION$$", version))
        .apply(kubeResource)
    } else kubeResource

  def authorizeDocker(kubeResource: Json): Json = {
    $.kind.string.getOption(kubeResource).map(_.toLowerCase) match {
      case Some("deployment") =>
        $.spec.template.spec.obj
          .modify(
            _.add("imagePullSecrets", List(Map("name" -> "local-docker-registry-secret")).asJson)
          )
          .apply(kubeResource)
      case _ => kubeResource
    }
  }

  def paint(kubeResource: Json, color: Color): Json = {
    $.kind.string.getOption(kubeResource).map(_.toLowerCase) match {
      case Some("deployment") =>
        $.metadata.labels.obj
          .modify(_.add("color", Json.fromString(color.toString)))
          .andThen(
            $.spec.selector.matchLabels.obj.modify(_.add("color", Json.fromString(color.toString)))
          )
          .andThen(
            $.spec.template.metadata.labels.obj
              .modify(_.add("color", Json.fromString(color.toString)))
          )
          .andThen($.metadata.name.string.modify(_ + "-" + color.toString))
          .apply(kubeResource)
      case _ => kubeResource
    }
  }

  def switch(kubeResource: Json, serviceName: String): (Option[Color], Json) = {
    import cats.data.State
    import cats.syntax.option._

    $.kind.string.getOption(kubeResource).map(_.toLowerCase) match {
      case Some("virtualservice") =>
        $.spec.http.each.route.json
          .modifyF { route =>
            if ($.each.destination.host.string
                  .getAll(route)
                  .map(_.toLowerCase)
                  .contains(serviceName)) {
              $.each.destination.subset.string
                .modifyF(old => {
                  val current = Color.from(old)
                  State.set(current.some).map(_ => current.switch.toString)
                })(route)
            } else State.pure[Option[Color], Json](route)
          }(kubeResource)
          .run(None)
          .value
      case _ => (None, kubeResource)
    }
  }

  def setupStageRouting(kubeResource: Json, currentProdColor: Color): Json = {
    $.kind.string.getOption(kubeResource).map(_.toLowerCase) match {
      case Some("virtualservice") =>
        val hasRouteWithNoDestination = $.spec.http.each.route.arr
          .find(!_.exists($.destination.obj.getOption(_).nonEmpty))
          .apply(kubeResource)
          .nonEmpty

        if (hasRouteWithNoDestination)
          sys.error("ROUTE WITH NO DESTINATION")

        $.spec.http.json
          .getOption(kubeResource)
          .map { http =>
            val stageRoute = $.each.json
              .modify(route =>
                $.route.each.destination.obj
                  .modify(_.add("subset", Json.fromString(currentProdColor.switch.toString)))
                  .andThen(
                    $.`match`.each.obj.modify(
                      _.deepMerge(
                        Map(
                          "headers" -> Map(
                            "cookie" -> Map(
                              "regex" -> s".*stage=\\.*(${$.route.each.destination.host.string.getAll(route).head.toLowerCase})\\.*"
                            )
                          )
                        ).asJsonObject
                      )
                    )
                  )
                  .apply(route)
              )
              .apply(http)
              .asArray

            val prodRoute = $.each.json
              .modify(
                $.route.each.destination.obj
                  .modify(_.add("subset", Json.fromString(currentProdColor.toString)))
              )
              .apply(http)
              .asArray

            $.spec.http.arr
              .set((stageRoute ++ prodRoute).flatten.toVector)
              .apply(kubeResource)
          }
          .getOrElse(kubeResource)
      case Some("destinationrule") => kubeResource
      case _                       => kubeResource
    }
  }

}
