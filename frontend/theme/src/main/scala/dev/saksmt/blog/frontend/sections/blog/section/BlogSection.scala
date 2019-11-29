package dev.saksmt.blog.frontend.sections.blog.section

import dev.saksmt.blog.frontend.core.Page
import dev.saksmt.blog.frontend.core.section.AbstractSection

class BlogSection(rootPage: Page) extends AbstractSection("Blog", "/blog") {
  override def route: Route = {
    case Root => rootPage
  }
}
