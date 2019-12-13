package dev.saksmt.blog.frontend.sections.about.section

import dev.saksmt.blog.frontend.core.routing.PageLocation
import dev.saksmt.blog.frontend.core.section.AbstractSection
import dev.saksmt.blog.frontend.sections.about.page.{AboutPage, BuiltWithPage}

class AboutSection(aboutPage: AboutPage, builtWithPage: BuiltWithPage) extends AbstractSection(aboutPage.title, PageLocation.About().sectionPagePath) {
  override def route: Route = {
    case Root => aboutPage
    case Root / "built-with" => builtWithPage
  }
}
