package dev.saksmt.blog.frontend.sections.blog.wiring.impl

import dev.saksmt.blog.frontend.core.Page
import dev.saksmt.blog.frontend.core.section.Section
import dev.saksmt.blog.frontend.sections.blog.section.BlogSection
import dev.saksmt.blog.frontend.sections.blog.wiring.BlogSectionModule
import dev.saksmt.blog.frontend.staticPages.error.WIPPage
import com.softwaremill.macwire._

import scala.annotation.unused

trait BlogSectionModuleImpl extends BlogSectionModule {
  @unused private val rootPage: Page = WIPPage

  override val blogSection: Section = wire[BlogSection]
}
