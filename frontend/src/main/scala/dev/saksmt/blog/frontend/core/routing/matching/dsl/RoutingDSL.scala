package dev.saksmt.blog.frontend.core.routing.matching.dsl

import dev.saksmt.blog.frontend.core.Page
import dev.saksmt.blog.frontend.core.routing.PageLocation

trait RoutingDSL {
  type Router = PageLocation => Page
  val Root: Path = impl.Path.Root

  type Path = impl.Path

  val / : impl./.type = impl./
  val -> : impl.->.type = impl.->
}
