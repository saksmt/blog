package dev.saksmt.blog.frontend.sections.about.wiring.impl

import com.github.ghik.silencer.silent
import dev.saksmt.blog.frontend.core.section.Section
import dev.saksmt.blog.frontend.sections.about.page.{AboutPage, BuiltWithPage}
import dev.saksmt.blog.frontend.sections.about.wiring.AboutSectionModule
import com.softwaremill.macwire._
import dev.saksmt.blog.frontend.sections.about.section.AboutSection
import dev.saksmt.blog.frontend.wiring.NavigationModule

@silent
trait AboutSectionModuleImpl extends AboutSectionModule { this: NavigationModule =>
  private val aboutPage: AboutPage = wire[AboutPage]
  private val builtWithPage: BuiltWithPage = wire[BuiltWithPage]

  override val aboutSection: Section = wire[AboutSection]
}
