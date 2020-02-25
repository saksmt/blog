package dev.saksmt.blog.frontend.staticPages.error

import dev.saksmt.blog.frontend.core.Page
import dev.saksmt.blog.frontend.core.dom.Renderable

object WIPPage extends Page("404") {
  override def content: Renderable = (
    <h1>Work in progress</h1>
    <p>Content of this page will be available soon (probably).</p>
  )
}
