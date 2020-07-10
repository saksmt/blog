import common._
import dependencies._

val backend = mkProject("backend", ".")
  .deps(
    finch,
    tapir,
    tethys,
    cats,
    monix,
    supertagged
  )
  .dependsOn(modules.shared)
  .withMacroAnnotations()
