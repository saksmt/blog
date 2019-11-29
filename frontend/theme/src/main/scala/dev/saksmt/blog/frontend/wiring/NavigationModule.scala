package dev.saksmt.blog.frontend.wiring

import dev.saksmt.blog.frontend.core.routing.navigation.NavigationSchema

trait NavigationModule {
  def navigationSchema: NavigationSchema
}
