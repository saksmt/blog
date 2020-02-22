import common._
import dependencies._

val backend = mkProject("backend", ".")
  .deps(
    finch,
    tethys,

    cats,
    monix,
    supertagged
  ).settings(
    addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.1" cross CrossVersion.full)
)