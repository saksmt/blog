package dev.saksmt.blog.frontend.core.routing.navigation

import dev.saksmt.blog.frontend.core.routing.PageLocation
import mhtml.Var

trait NavigationSchema {
  def buildUri(location: PageLocation): String
  def initialize(): Var[PageLocation]
}
