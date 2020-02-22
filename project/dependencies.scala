import sbt._
import sbt.librarymanagement.ModuleID
import sbt.Keys.libraryDependencies
import org.portablescala.sbtplatformdeps.PlatformDepsPlugin.autoImport.toPlatformDepsGroupID

object dependencies {
  object versions {
    val silencer = "1.4.4"

    val macwire = "2.3.3"
    val scribe = "2.7.10"
    val enumeratum = "1.5.13"

    val circe = "0.11.1"
    val enumeratumCirce = "1.5.22"

    val jsdom = "0.9.7"
    val mhtml = "0.4.0"

    val finch = "0.31.0"

    val tethys = "0.10.0"

    val cats = "2.0.0"
    val monix = "3.1.0"

    val derevo = "0.10.5"
    val supertagged = "1.5"
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

  val circe = Dep(compilerPlugin("org.scalamacros" % "paradise" % "2.1.1" cross CrossVersion.full) +: Seq(
    "io.circe" %% "circe-core",
    "io.circe" %% "circe-generic",
    "io.circe" %% "circe-parser"
  ).map(_ % versions.circe) : _*)

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
