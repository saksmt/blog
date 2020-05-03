import common._

lazy val shared = mkProject("shared")
  .settings(
    sourceGenerators in Compile += Def.task {
      val sourceDir = (sourceManaged in Compile).value
      val versionFile = sourceDir / "dev" / "saksmt" / "blog" / "buildInfo.scala"

      IO.write(versionFile, BuildInfo.generator.value)

      Seq(versionFile)
    }
  )
  .enablePlugins(ScalaJSPlugin)
