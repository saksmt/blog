package dev.saksmt.blog.frontend.core.routing.navigation

import dev.saksmt.blog.frontend.core.routing.PageLocation
import mhtml.Var
import org.scalajs.dom.Window

import scala.xml.{Elem, UnprefixedAttribute}

class SimpleHashNavigationSchema(window: Window) extends NavigationSchema {
  override def buildLink(location: PageLocation)(link: Elem): Elem = Elem(
    link.prefix,
    link.label,
    UnprefixedAttribute[String]("href", buildUri(location), link.attributes1),
    link.scope,
    link.minimizeEmpty,
    link.child:_*
  )

  override def initialize(): Var[PageLocation] = {
    val state = Var(buildLocation(window.location.hash))
    window.onhashchange = _ => state.update(_ => buildLocation(window.location.hash))
    state
  }

  private def buildUri(location: PageLocation): String = s"#${location.sectionPagePath}${location.path}".replaceAllLiterally("//", "/")

  private def buildLocation(rawUri: String): PageLocation = {
    val uri = rawUri.stripPrefix("#")
    scribe.info(s"Building location for: $uri")

    val section = "/" + uri.dropWhile(_ == '/').takeWhile(_ != '/')
    val page = {
      val pagePath = uri.stripPrefix(section)
      if (pagePath == "") {
        "/"
      } else {
        pagePath
      }
    }

    scribe.info(s"Parsed: section=$section; page=$page")

    PageLocation(section, page)
  }
}
