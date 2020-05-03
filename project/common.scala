import sbt._
import sbt.Keys.{compile, organization, scalaVersion, scalacOptions, version}

object common {
  private val appVersion = "0.0.1"

  def mkProject(name: String, file: String = "."): Project = {
    setupVersion(Project(name, sbt.file(file)))
      .settings(
        organization := "dev.saksmt.blog",
        scalaVersion := "2.13.2",
        // format: off
        scalacOptions ++= Seq(
          "-explaintypes",
          
          "-feature",
          "-deprecation",
          
          "-Ybackend-parallelism", java.lang.Runtime.getRuntime.availableProcessors.toString,
          
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
        // format: on
      )
  }

  private def setupVersion(project: Project): Project = {
    import com.typesafe.sbt.SbtGit.git

    project.settings(
      version := {
        val snapshot = git.gitCurrentTags.value.contains("v" + appVersion)
        val resultVersion = if (snapshot) {
          appVersion
        } else {
          s"${appVersion}-snapshot"
        }

        val imageTag = if (snapshot) {
          val gitCommit = git.gitHeadCommit.value.getOrElse("")

          val sanitizedBranchName = git.gitCurrentBranch.value
            .replace("/", "_")
            .replace(":", "_")

          s"${sys.env.getOrElse("buildNumber", "")}.$sanitizedBranchName.$gitCommit"
        } else appVersion

        println(s"::set-env name=IMAGE_TAG::$imageTag")
        println(s"::set-env name=APP_VERSION::$resultVersion")

        resultVersion
      }
    )
  }

  implicit final class CommonProjectProjectPimp(val project: Project) extends AnyVal {
    def withMacroAnnotations(in: (ConfigKey, Scoped) = (Compile, compile)): Project =
      project.settings(
        scalacOptions ++= Seq("-Ymacro-annotations")
      )
  }
}
