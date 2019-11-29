package dev.saksmt.blog.frontend.sections.about.section

import dev.saksmt.blog.frontend.core.Page
import dev.saksmt.blog.frontend.core.section.AbstractSection

class AboutSection(aboutPage: Page) extends AbstractSection(aboutPage.title, "/about") {
  override def route: Route = {
    case Root => aboutPage
  }
}
