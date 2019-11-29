package dev.saksmt.blog.frontend.staticPages.error

import dev.saksmt.blog.frontend.core.Page
import dev.saksmt.blog.frontend.core.dom.Renderable

object NotFoundPage extends Page("404") {
  override def content: Renderable = <p>404</p>
}
