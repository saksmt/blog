package dev.saksmt.blog.frontend.sections.main.page

import dev.saksmt.blog.frontend.core.Page
import dev.saksmt.blog.frontend.core.dom.Renderable

object MainPage extends Page("/") {
  override val content: Renderable = <p>Hello!</p>
}
