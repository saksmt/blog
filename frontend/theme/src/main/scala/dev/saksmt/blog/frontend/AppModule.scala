package dev.saksmt.blog.frontend

import com.github.ghik.silencer.silent
import dev.saksmt.blog.frontend.core.section.{Section, SectionRouter}
import dev.saksmt.blog.frontend.sections.about.wiring.impl.AboutSectionModuleImpl
import dev.saksmt.blog.frontend.sections.main.wiring.impl.MainSectionModuleImpl
import dev.saksmt.blog.frontend.wiring.impl.{DomModuleImpl, NavigationModuleImpl}
import com.softwaremill.macwire._
import dev.saksmt.blog.frontend.components.menu.{Menu, MenuComponent, MenuItem}
import dev.saksmt.blog.frontend.core.routing.PageLocation
import dev.saksmt.blog.frontend.sections.blog.wiring.impl.BlogSectionModuleImpl
import dev.saksmt.blog.frontend.sections.projects.wiring.impl.ProjectsSectionModuleImpl
import dev.saksmt.blog.frontend.staticPages.error.NotFoundPage
import mhtml.mount

import scala.xml.Atom

class AppModule
  extends DomModuleImpl
    with NavigationModuleImpl

    // mixin order affect position in menu
    with AboutSectionModuleImpl
    with BlogSectionModuleImpl
    with ProjectsSectionModuleImpl
    with MainSectionModuleImpl {

  private val sections: Seq[Section] = {
    val set = wireSet[Section]
    set.toSeq
  }
  private val sectionRouter = wire[SectionRouter]

  @silent private val menu = Menu(
    MenuItem("@saksmt", PageLocation(mainSection.path, "/")),
    sections.filter(_.path != mainSection.path).map { it => MenuItem(it.name, PageLocation(it.path, "/"))  }
  )
  private val menuComponent = wire[MenuComponent]

  def run(): Unit = {
    val currentLocation = navigationSchema.initialize()
    val currentPage = currentLocation.map(sectionRouter.router.applyOrElse(_, (_: PageLocation) => NotFoundPage))

    val menuElement = document.getElementById("main-menu")
    val contentElement = document.getElementById("content")

    mount(menuElement, currentLocation.map(menuComponent.build).flatMap(_.render))
    mount(contentElement, currentPage.flatMap(_.content.render))
    mount(document.getElementsByTagName("title").item(0), currentPage.map(_.title).map(new Atom(_)))
  }
}
