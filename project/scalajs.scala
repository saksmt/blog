import org.scalajs.sbtplugin.ScalaJSPlugin
import org.scalajs.sbtplugin.ScalaJSPlugin.AutoImport._
import sbt.Project

object scalajs {
  implicit final class ScalaJSProjectPimp(val project: Project) extends AnyVal {
    def asScalaJsLib(): Project =
      project
        .enablePlugins(ScalaJSPlugin)
        .settings(
          scalaJSLinkerConfig in fullOptJS ~= {
            _.withModuleKind(ModuleKind.NoModule)
              .withClosureCompiler(true)
              .withOptimizer(true)
              .withSourceMap(true)
          },
          scalaJSLinkerConfig in fastOptJS ~= {
            _.withModuleKind(ModuleKind.NoModule)
              .withClosureCompiler(true)
              .withOptimizer(true)
              .withSourceMap(true)
          },
          scalaJSOptimizerOptions in fullOptJS ~= { _.withUseClosureCompiler(true) },
          scalaJSOptimizerOptions in fastOptJS ~= { _.withUseClosureCompiler(true) }
        )
  }
}
