import common._
import dependencies._

val webpackKey = TaskKey[Unit]("webpack", "")
val webpackProdKey = TaskKey[Unit]("webpackProd", "")

lazy val frontend = mkProject("frontend", ".")
  .deps(
    silencer,
    macwire
  )
  .settings(
    // fucking sbt magic ><
    libraryDependencies ++= Seq(
      "com.outr" %%% "scribe" % versions.scribe,
      "org.scala-js" %%% "scalajs-dom" % versions.jsdom,
      "in.nvilla" %%% "monadic-html" % versions.mhtml
    ),

    scalaJSLinkerConfig ~= {
      _.withModuleKind(ModuleKind.ESModule)
    },

    artifactPath in(Compile, fastOptJS) := target.value / "scala.js",
    artifactPath in(Compile, fullOptJS) := target.value / "scala.js",

    webpackKey := {
      import sys.process._

      if (Process("npm run webpack", Some(baseDirectory.value)).! != 0) sys.error("npm|webpack failed")
    },
    webpackKey := webpackKey.dependsOn(fastOptJS in Compile).value,

    webpackProdKey := {
      import sys.process._

      if (Process("npm run webpack", Some(baseDirectory.value), "NODE_ENV" -> "production").! != 0) sys.error("npm|webpack failed")
    },
    webpackProdKey := webpackProdKey.dependsOn(fullOptJS in Compile).value
  )
  .enablePlugins(ScalaJSPlugin)


addCommandAlias("build", "webpack")
addCommandAlias("buildProd", "webpackProd")
