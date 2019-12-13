package dev.saksmt.blog.frontend.staticPages.error

import dev.saksmt.blog.frontend.core.Page
import dev.saksmt.blog.frontend.core.dom.Renderable

object NotFoundPage extends Page("404") {
  override def content: Renderable = (
    <h1>404</h1>
    <p>Hey! This page doesn't exist!</p>
    <p>Are you sure you've visited correct address? If so contact me using links in menu bar</p>
  )
}
