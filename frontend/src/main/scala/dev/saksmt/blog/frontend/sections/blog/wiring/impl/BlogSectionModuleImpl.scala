package dev.saksmt.blog.frontend.sections.blog.wiring.impl

import com.github.ghik.silencer.silent
import dev.saksmt.blog.frontend.core.Page
import dev.saksmt.blog.frontend.core.section.Section
import dev.saksmt.blog.frontend.sections.blog.section.BlogSection
import dev.saksmt.blog.frontend.sections.blog.wiring.BlogSectionModule
import dev.saksmt.blog.frontend.staticPages.error.WIPPage
import com.softwaremill.macwire._

@silent("never used")
trait BlogSectionModuleImpl extends BlogSectionModule {
  private val rootPage: Page = WIPPage

  override val blogSection: Section = wire[BlogSection]
}
