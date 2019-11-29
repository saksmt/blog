package dev.saksmt.blog.frontend.pages

import dev.saksmt.blog.frontend.core.dom.Renderable
import dev.saksmt.blog.frontend.{Page, PageLocation}

object MainPage extends Page("/", Some(PageLocation("/", ""))) {
  override val content: Renderable = <p>Hello!</p>
}
