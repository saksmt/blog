package dev.saksmt.blog.frontend

import dev.saksmt.blog.frontend.components.MenuComponent
import dev.saksmt.blog.frontend.highlight.Highlight
import dev.saksmt.blog.frontend.pages.error.NotFoundPage
import dev.saksmt.blog.frontend.pages.{AboutPage, MainPage}
import mhtml.{Var, mount}

import scala.scalajs.js.annotation.JSExportTopLevel
import org.scalajs.dom._

import scala.xml.Atom

object Main {

  @JSExportTopLevel("runScalaApp")
  def main(highlight: Highlight): Unit = document.addEventListener("DOMContentLoaded", (_: Event) => runApp())

  private def runApp(): Unit = {
    val menu = Menu(
      MenuItem("@saksmt",  MainPage),
      Seq(
        AboutPage,
        Page("Projects", NotFoundPage.content, PageLocation("/projects", "")),
        Page("Blog", NotFoundPage.content, PageLocation("/blog", ""))
      )
    )
    val router = new Router(
      menu,
      _ => NotFoundPage
    )
    val currentPage = Var(router.route(path = window.location.hash)) // todo: route this
    window.onhashchange = _ => currentPage := router.route(window.location.hash)

    val menuComponent = new MenuComponent(menu)
    val menuElement = document.getElementById("main-menu")
    val contentElement = document.getElementById("content")

    mount(menuElement, currentPage.map(_.location).map(menuComponent.build).flatMap(_.render))
    mount(contentElement, currentPage.flatMap(_.content.render))
    mount(document.getElementsByTagName("title").item(0), currentPage.map(_.title).map(new Atom(_)))
  }
}