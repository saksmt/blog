package dev.saksmt.blog.frontend.sections.projects.wiring.impl

import com.github.ghik.silencer.silent
import dev.saksmt.blog.frontend.core.Page
import dev.saksmt.blog.frontend.core.section.Section
import dev.saksmt.blog.frontend.sections.projects.sections.ProjectsSection
import dev.saksmt.blog.frontend.sections.projects.wiring.ProjectsSectionModule
import dev.saksmt.blog.frontend.staticPages.error.NotFoundPage
import com.softwaremill.macwire._

@silent("never used")
trait ProjectsSectionModuleImpl extends ProjectsSectionModule {
  private val rootPage: Page = NotFoundPage

  override val projectsSection: Section = wire[ProjectsSection]
}
