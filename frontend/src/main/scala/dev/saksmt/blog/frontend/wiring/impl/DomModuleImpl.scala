package dev.saksmt.blog.frontend.wiring.impl

import dev.saksmt.blog.frontend.wiring.DomModule
import org.scalajs.dom.Window

trait DomModuleImpl extends DomModule {
  override val window: Window = org.scalajs.dom.window
}
