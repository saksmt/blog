enablePlugins(ScalaJSPlugin)

scalaVersion := "2.12.8"

lazy val mhtmlV = "0.4.0-RC1"

libraryDependencies += "in.nvilla" %%% "monadic-html" % mhtmlV
libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "0.9.7"
libraryDependencies += "com.outr" %%% "scribe" % "2.7.3"

scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.ESModule) }

artifactPath in (Compile, fastOptJS) := target.value / "scala.js"
artifactPath in (Compile, fullOptJS) := target.value / "scala.js"

val webpackKey = TaskKey[Unit]("webpack", "")
val webpackProdKey = TaskKey[Unit]("webpackProd", "")

webpackKey := {
  import sys.process._

  if ("npm run webpack".! != 0) sys.error("npm|webpack failed")
}
webpackKey := webpackKey.dependsOn(fastOptJS in Compile).value

webpackProdKey := {
  import sys.process._

  if (Process("npm run webpack", None, "NODE_ENV" -> "production").! != 0) sys.error("npm|webpack failed")
}
webpackProdKey := webpackProdKey.dependsOn(fullOptJS in Compile).value

addCommandAlias("build", "webpack")
addCommandAlias("buildProd", "webpackProd")
