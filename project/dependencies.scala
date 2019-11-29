import sbt._
import sbt.librarymanagement.ModuleID
import sbt.Keys.libraryDependencies
import org.portablescala.sbtplatformdeps.PlatformDepsPlugin.autoImport.toPlatformDepsGroupID

object dependencies {
  object versions {
    val silencer = "1.4.4"

    val macwire = "2.3.3"
    val scribe = "2.7.3"

    val jsdom = "0.9.7"
    val mhtml = "0.4.0-RC1"
  }

  case class Dep(moduleIds: ModuleID*)

  implicit class DependenciesProjectPimp(val project: Project) extends AnyVal {
    def deps(deps: Dep*): Project = project.settings(libraryDependencies ++= deps.flatMap(_.moduleIds))
  }

  val silencer = Dep(
    compilerPlugin("com.github.ghik" % "silencer-plugin" % versions.silencer cross CrossVersion.full),
    "com.github.ghik" % "silencer-lib" % versions.silencer % Provided cross CrossVersion.full
  )

  val macwire = Dep("com.softwaremill.macwire" %% "macros" % versions.macwire)
}
