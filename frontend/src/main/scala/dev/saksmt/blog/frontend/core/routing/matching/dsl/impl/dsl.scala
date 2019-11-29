package dev.saksmt.blog.frontend.core.routing.matching.dsl.impl

import dev.saksmt.blog.frontend.core.routing.PageLocation

object / {
  def unapply(path: Path): Option[(Path, String)] = {
    val lastSegment = path.path.reverse.takeWhile(_ != '/').reverse
    val parentPath = path.path.reverse.dropWhile(_ != '/').reverse

    scribe.info(s"Parsed path $path into $lastSegment and $parentPath")

    Some((new Path(parentPath), lastSegment))
  }
}

object -> {
  def unapply(pageLocation: PageLocation): Option[(String, Path)] = Some((pageLocation.sectionPagePath, new Path(pageLocation.path)))
}

class Path private[impl](private[impl] val path: String) extends AnyVal
object Path {
  val Root = new Path("/")
}
