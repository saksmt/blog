import common._
import dependencies._
import scalajs._
import webpack._

lazy val frontend = mkProject("frontend", ".")
  .deps(
    macwire
  )
  .dependsOn(modules.shared)
  .settings(
    // fucking sbt magic ><
    libraryDependencies ++= Seq(
      "com.outr"     %%% "scribe" % versions.scribe,
      "org.scala-js" %%% "scalajs-dom" % versions.jsdom,
      "in.nvilla"    %%% "monadic-html" % versions.mhtml,
      "com.beachape" %%% "enumeratum" % versions.enumeratum
    ),
    artifactPath in (Compile, fastOptJS) := target.value / "scala.js",
    artifactPath in (Compile, fullOptJS) := target.value / "scala.js"
  )
  .asScalaJsLib()
  .withWebpack()

addCommandAlias("build", "webpack")
addCommandAlias("buildProd", "webpackProd")
