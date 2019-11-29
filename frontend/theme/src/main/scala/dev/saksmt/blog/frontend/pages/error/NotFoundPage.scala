package dev.saksmt.blog.frontend.pages.error

import dev.saksmt.blog.frontend.core.dom.Renderable
import dev.saksmt.blog.frontend.Page

object NotFoundPage extends Page("404", None) {
  override def content: Renderable = <p>404</p>
}
