import common._
import dependencies._

val backend = mkProject("backend", ".")
  .deps(
    finch,
    tethys,

    cats,
    monix,
    supertagged
  ).withMacroAnnotations()