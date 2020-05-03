import sbt._
import sbt.librarymanagement.ModuleID
import sbt.Keys.libraryDependencies
import org.portablescala.sbtplatformdeps.PlatformDepsPlugin.autoImport.toPlatformDepsGroupID

object dependencies {
  object versions {
    val macwire = "2.3.3"
    val scribe = "2.7.10"
    val enumeratum = "1.5.13"

    val circe = "0.11.1"
    val enumeratumCirce = "1.5.22"

    val jsdom = "0.9.8"
    val mhtml = "0.4.0"

    val finch = "0.32.1"

    val tethys = "0.11.0"

    val cats = "2.1.1"
    val monix = "3.2.1"

    val derevo = "0.11.2"
    val supertagged = "1.5"
  }

  case class Dep(moduleIds: ModuleID*)

  implicit class DependenciesProjectPimp(val project: Project) extends AnyVal {
    def deps(deps: Dep*): Project = project.settings(libraryDependencies ++= deps.flatMap(_.moduleIds))
  }

  val macwire = Dep("com.softwaremill.macwire" %% "macros" % versions.macwire)

  val enumeratum = Dep(
    "com.beachape" %% "enumeratum" % versions.enumeratum
  )

  val finch = Dep(
    "com.github.finagle" %% "finchx-core" % versions.finch
  )

  val tethys = Dep(
    "com.tethys-json" %% "tethys" % versions.tethys,
    "org.manatki" %% "derevo-tethys" % versions.derevo
  )

  val cats = Dep(
    "org.typelevel" %% "cats-core" % versions.cats,
    "org.typelevel" %% "cats-effect" % versions.cats
  )

  val monix = Dep("io.monix" %% "monix" % versions.monix)

  val supertagged = Dep("org.rudogma" %% "supertagged" % versions.supertagged)
}
