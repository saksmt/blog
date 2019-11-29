package dev.saksmt.blog.frontend.wiring.impl

import dev.saksmt.blog.frontend.core.routing.navigation.{NavigationSchema, SimpleHashNavigationSchema}
import dev.saksmt.blog.frontend.wiring.{DomModule, NavigationModule}
import com.softwaremill.macwire.wire

trait NavigationModuleImpl extends NavigationModule { this: DomModule =>
  override val navigationSchema: NavigationSchema = wire[SimpleHashNavigationSchema]
}
