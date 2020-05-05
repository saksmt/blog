import common._
import dependencies._
import sbtassembly.AssemblyPlugin.defaultShellScript

lazy val `deploy-script` = mkProject("deploy-script")
  .deps(
    cats,
    circe.core,
    circe.yaml,
    circe.optics,
    monocle.core
  )
  .settings(
    mainClass in assembly := Some("dev.saksmt.blog.deploy.DeployApp"),
    assemblyJarName in assembly := "deploy",
    assemblyOutputPath in assembly := (baseDirectory in project).value / ".." / "deploy",
    assemblyOption in assembly := (assemblyOption in assembly).value
      .copy(prependShellScript = Some(defaultShellScript))
  )
