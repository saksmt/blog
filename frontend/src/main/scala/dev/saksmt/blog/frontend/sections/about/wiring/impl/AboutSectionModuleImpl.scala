package dev.saksmt.blog.frontend.sections.about.wiring.impl

import dev.saksmt.blog.frontend.core.section.Section
import dev.saksmt.blog.frontend.sections.about.page.{AboutPage, BuiltWithPage}
import dev.saksmt.blog.frontend.sections.about.wiring.AboutSectionModule
import com.softwaremill.macwire._
import dev.saksmt.blog.frontend.sections.about.section.AboutSection
import dev.saksmt.blog.frontend.wiring.NavigationModule

import scala.annotation.unused

trait AboutSectionModuleImpl extends AboutSectionModule { this: NavigationModule =>
  @unused private val aboutPage: AboutPage = wire[AboutPage]
  @unused private val builtWithPage: BuiltWithPage = wire[BuiltWithPage]

  override val aboutSection: Section = wire[AboutSection]
}
