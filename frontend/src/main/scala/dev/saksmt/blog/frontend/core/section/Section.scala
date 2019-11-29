package dev.saksmt.blog.frontend.core.section

import dev.saksmt.blog.frontend.core.Page
import dev.saksmt.blog.frontend.core.routing.matching.dsl.impl.Path

trait Section {
  type Route = PartialFunction[Path, Page]
  def route: Route

  def path: String
  def name: String
}


