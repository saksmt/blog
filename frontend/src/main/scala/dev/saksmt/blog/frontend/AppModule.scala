package dev.saksmt.blog.frontend

import dev.saksmt.blog.frontend.core.section.{Section, SectionRouter}
import dev.saksmt.blog.frontend.sections.about.wiring.impl.AboutSectionModuleImpl
import dev.saksmt.blog.frontend.sections.main.wiring.impl.MainSectionModuleImpl
import dev.saksmt.blog.frontend.wiring.impl.{
  BuildInfoModuleImpl,
  DomModuleImpl,
  NavigationModuleImpl
}
import com.softwaremill.macwire._
import dev.saksmt.blog.frontend.components.menu.{Menu, MenuComponent, MenuItem}
import dev.saksmt.blog.frontend.config.AppConfig
import dev.saksmt.blog.frontend.core.routing.PageLocation
import dev.saksmt.blog.frontend.sections.blog.wiring.impl.BlogSectionModuleImpl
import dev.saksmt.blog.frontend.sections.projects.wiring.impl.ProjectsSectionModuleImpl
import dev.saksmt.blog.frontend.staticPages.error.NotFoundPage
import dev.saksmt.blog.frontend.wiring.ConfigModule
import mhtml.mount

import scala.annotation.unused
import scala.xml.Atom

class AppModule(override val config: AppConfig)
    extends DomModuleImpl
    with ConfigModule
    with BuildInfoModuleImpl
    with NavigationModuleImpl

    // mixin order affect position in menu
    with AboutSectionModuleImpl
    with BlogSectionModuleImpl
    with ProjectsSectionModuleImpl
    with MainSectionModuleImpl {

  override val isDeveloper: Boolean = cookies.get("stage").exists(_.contains("frontend"))

  private val sections: Seq[Section] = {
    val set = wireSet[Section].map(Some.apply) ++ wireSet[Option[Section]]
    set.toSeq.flatten
  }
  private val sectionRouter = wire[SectionRouter]

  @unused private val menu = Menu(
    MenuItem("@saksmt", PageLocation(mainSection.path, "/")),
    sections.filter(_.path != mainSection.path).map { it =>
      MenuItem(it.name, PageLocation(it.path, "/"))
    }
  )
  private val menuComponent = wire[MenuComponent]

  def run(): Unit = {
    if (isDeveloper) {
      scribe.Logger.root.withMinimumLevel(scribe.Level.Debug).replace()
    } else {
      scribe.Logger.root.withMinimumLevel(scribe.Level.Error).replace()
    }

    val currentLocation = navigationSchema.initialize()
    scribe.info("Navigation schema initialized")
    val currentPage =
      currentLocation.map(sectionRouter.router.applyOrElse(_, (_: PageLocation) => NotFoundPage))

    val menuElement = document.getElementById("main-menu")
    val contentElement = document.getElementById("content")

    mount(menuElement, currentLocation.map(menuComponent.build).flatMap(_.render))
    mount(contentElement, currentPage.flatMap(_.content.render))
    mount(document.getElementsByTagName("title").item(0), currentPage.map(_.title).map(new Atom(_)))
    mount(document.getElementById("footer"), footerContent.render)

    ()
  }
}
