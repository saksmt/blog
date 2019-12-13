package dev.saksmt.blog.frontend.sections.projects.sections

import dev.saksmt.blog.frontend.core.Page
import dev.saksmt.blog.frontend.core.routing.PageLocation
import dev.saksmt.blog.frontend.core.section.AbstractSection

class ProjectsSection(rootPage: Page) extends AbstractSection("Projects", PageLocation.Projects().sectionPagePath) {
  override def route: Route = {
    case Root => rootPage
  }
}
