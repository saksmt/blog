package dev.saksmt.blog.frontend.sections.blog.section

import dev.saksmt.blog.frontend.core.Page
import dev.saksmt.blog.frontend.core.routing.PageLocation
import dev.saksmt.blog.frontend.core.section.AbstractSection

class BlogSection(rootPage: Page) extends AbstractSection("Blog", PageLocation.Blog().sectionPagePath) {
  override def route: Route = {
    case Root => rootPage
  }
}
