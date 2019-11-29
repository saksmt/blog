package dev.saksmt.blog.frontend.sections.main

import dev.saksmt.blog.frontend.core.Page
import dev.saksmt.blog.frontend.core.section.AbstractSection

class MainSection(mainPage: Page) extends AbstractSection(mainPage.title, "/") {
  override def route: Route = {
    case Root => mainPage
  }
}
