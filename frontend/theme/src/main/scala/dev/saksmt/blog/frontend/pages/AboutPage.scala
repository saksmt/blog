package dev.saksmt.blog.frontend.pages

import dev.saksmt.blog.frontend.core.dom.Renderable
import dev.saksmt.blog.frontend.{Page, PageLocation}

object AboutPage extends Page("About", Some(PageLocation("/about", ""))) {
  override val content: Renderable = (
    <h1>About</h1>
    <p>Something about me</p>
  )
}
