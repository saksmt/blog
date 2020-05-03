package dev.saksmt.blog.frontend.sections.main.wiring.impl

import dev.saksmt.blog.frontend.core.Page
import dev.saksmt.blog.frontend.core.section.Section
import dev.saksmt.blog.frontend.sections.main.MainSection
import dev.saksmt.blog.frontend.sections.main.page.MainPage
import dev.saksmt.blog.frontend.sections.main.wiring.MainSectionModule
import com.softwaremill.macwire.wire

import scala.annotation.unused

trait MainSectionModuleImpl extends MainSectionModule {
  @unused private val mainPage: Page = MainPage

  override val mainSection: Section = wire[MainSection]
}
