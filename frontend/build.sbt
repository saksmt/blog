import common._
import dependencies._
import org.scalajs.core.tools.linker.backend.OutputMode

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
      "in.nvilla" %%% "monadic-html" % versions.mhtml,
      "com.beachape" %%% "enumeratum" % versions.enumeratum exclude("org.scala-lang.modules", "scala-xml")
    ),

    scalaJSLinkerConfig ~= { _
      .withModuleKind(ModuleKind.ESModule)
      .withClosureCompiler(true)
      .withOptimizer(true)
      .withSourceMap(true)
    },

    scalaJSOptimizerOptions ~= { _.withUseClosureCompiler(true) },

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
