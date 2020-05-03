package dev.saksmt.blog.frontend.wiring

import dev.saksmt.blog.frontend.core.dom.Renderable

trait BuildInfoModule {
  def footerContent: Renderable
}
