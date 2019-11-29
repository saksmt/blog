package dev.saksmt.blog.frontend

import dev.saksmt.blog.frontend.core.dom.Renderable

import scala.language.implicitConversions

abstract class Page(val title: String, val location: Option[PageLocation]) {
  def content: Renderable
}
object Page {
  def apply(title: String, content_ : Renderable): Page = new Page(title, None) { override val content = content_ }
  def apply(title: String, content_ : Renderable, location: PageLocation): Page = new Page(title, Some(location)) { override val content = content_ }
}
case class PageLocation(sectionPagePath: String, path: String) {
  def href: String = s"#$sectionPagePath$path"
}
case class Menu(mainPage: MenuItem, sections: Seq[MenuItem])

case class MenuItem(name: String, page: Page)
object MenuItem {
  implicit def menuItemForPage(page: Page): MenuItem = MenuItem(page.title, page)
}
