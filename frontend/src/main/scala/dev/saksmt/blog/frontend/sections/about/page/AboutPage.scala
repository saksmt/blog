package dev.saksmt.blog.frontend.sections.about.page

import dev.saksmt.blog.frontend.core.Page
import dev.saksmt.blog.frontend.core.dom.Renderable

object AboutPage extends Page("About") {
  override val content: Renderable = (
    <h1>About</h1>
    <p>Something about me</p>
  )
}
