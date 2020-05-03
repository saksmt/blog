import sbt._
import Keys.baseDirectory
import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._

object webpack {
  val webpack = TaskKey[Unit]("webpack", "")
  val webpackProd = TaskKey[Unit]("webpackProd", "")

  implicit final class WebpackProjectPimp(val project: Project) extends AnyVal {
    def withWebpack(): Project = project.settings(
      webpack := {
        import sys.process._

        if (Process("npm run webpack", Some(baseDirectory.value)).! != 0)
          sys.error("npm|webpack failed")
      },
      webpack := webpack.dependsOn(fastOptJS in Compile).value,
      webpackProd := {
        import sys.process._

        if (Process("npm run webpack", Some(baseDirectory.value), "NODE_ENV" -> "production").! != 0)
          sys.error("npm|webpack failed")
      },
      webpackProd := webpackProd.dependsOn(fullOptJS in Compile).value
    )
  }
}
