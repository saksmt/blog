package dev.saksmt.blog.frontend.sections.about.wiring.impl

import com.github.ghik.silencer.silent
import dev.saksmt.blog.frontend.core.Page
import dev.saksmt.blog.frontend.core.section.Section
import dev.saksmt.blog.frontend.sections.about.page.AboutPage
import dev.saksmt.blog.frontend.sections.about.wiring.AboutSectionModule
import com.softwaremill.macwire._
import dev.saksmt.blog.frontend.sections.about.section.AboutSection

@silent
trait AboutSectionModuleImpl extends AboutSectionModule {
  private val aboutPage: Page = AboutPage

  override val aboutSection: Section = wire[AboutSection]
}
