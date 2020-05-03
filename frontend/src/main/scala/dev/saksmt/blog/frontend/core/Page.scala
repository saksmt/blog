package dev.saksmt.blog.frontend.core

import dev.saksmt.blog.frontend.core.dom.Renderable

abstract class Page(val title: String) {
  def content: Renderable
}

object Page {
  def apply(title: String, content_ : Renderable): Page = new Page(title) {
    override val content = content_
  }
}
