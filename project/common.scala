import sbt.Project
import sbt.Keys.{organization, scalaVersion, scalacOptions}
import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport.isScalaJSProject

object common {
  def mkProject(name: String, file: String): Project = {
    Project(name, sbt.file(file))
      .settings(
        organization := "dev.saksmt.blog",
        scalaVersion := "2.12.10",
        scalacOptions ++=
          Seq(
            "-encoding", "utf-8",
            "-Ybackend-parallelism", Runtime.getRuntime.availableProcessors.toString,

            "-Ypartial-unification",
            "-explaintypes",
            "-feature",

            "-unchecked",
            "-Xcheckinit",
            "-Xfatal-warnings",
            "-Xfuture",
            "-Xlint:adapted-args",
            "-Xlint:by-name-right-associative",
            "-Xlint:constant",
            "-Xlint:inaccessible",
            "-Xlint:infer-any",
            "-Xlint:missing-interpolator",
            "-Xlint:nullary-override",
            "-Xlint:nullary-unit",
            "-Xlint:option-implicit",
            "-Xlint:private-shadow",
            "-Xlint:type-parameter-shadow",
            "-Xlint:unsound-match",
            "-Yno-adapted-args",
            "-Ywarn-dead-code",
            "-Ywarn-extra-implicit",
            "-Ywarn-inaccessible",
            "-Ywarn-infer-any",
            "-Ywarn-nullary-override",
            "-Ywarn-nullary-unit",
            "-Ywarn-numeric-widen",
            "-Ywarn-unused:implicits",
            "-Ywarn-unused:imports",
            "-Ywarn-unused:locals",
            "-Ywarn-unused:params",
            "-Ywarn-unused:patvars",
            "-Ywarn-unused:privates",
            "-Ywarn-value-discard"
          ) //++ (if (!isScalaJSProject.value) Seq("-target:8") else Seq())
      )
  }

  private val `scala 2.13 compiler options` = Seq (
    "-explaintypes",

    "-Ybackend-parallelism", Runtime.getRuntime.availableProcessors.toString,

    "-Xcheckinit",
    "-Xlint:nonlocal-return",
    "-Xlint:valpattern",
    "-Xlint:option-implicit",
    "-Xlint:private-shadow",
    "-Xlint:type-parameter-shadow",
    "-Xlint:adapted-args",
    "-Xlint:constant",
    "-Xlint:missing-interpolator",
    "-Xlint:inaccessible",
    "-Xlint:infer-any",
    "-Xlint:nullary-override",
    "-Xlint:nullary-unit",

    "-Wunused:imports",
    "-Wunused:patvars",
    "-Wunused:privates",
    "-Wunused:locals",
    "-Wunused:params",

    "-Wself-implicit",
    "-Wdead-code",
    "-Wvalue-discard",

    "-Werror"
  )
}
