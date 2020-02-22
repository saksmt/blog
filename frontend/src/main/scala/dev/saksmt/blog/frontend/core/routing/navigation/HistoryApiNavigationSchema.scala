package dev.saksmt.blog.frontend.core.routing.navigation

import dev.saksmt.blog.frontend.core.routing.PageLocation
import mhtml.Var
import org.scalajs.dom.raw.Event
import org.scalajs.dom.{Node, Window}

import scala.xml.{Elem, UnprefixedAttribute}

class HistoryApiNavigationSchema(window: Window, baseUri: String) extends NavigationSchema {
  private lazy val currentLocation = Var[PageLocation](buildLocation(window.location.pathname + window.location.search))

  override def buildLink(location: PageLocation)(link: Elem): Elem = {
    val uri = buildUri(location)
    Elem(link.prefix, link.label, UnprefixedAttribute(
      "href", uri,
      UnprefixedAttribute(
        "mhtml-onmount", (node: Node) => {
          node.addEventListener("click", (e: Event) => {
            e.preventDefault()
            e.stopPropagation()
            currentLocation := location
            window.history.pushState(null, "", uri)

            true
          })
        }, link.attributes1
      )
    ), link.scope, link.minimizeEmpty, link.child:_*)
  }
  override def initialize(): Var[PageLocation] = {
    window.addEventListener("popstate", (_: Event) => {
      currentLocation := buildLocation(window.location.pathname + window.location.search)
    })
    currentLocation
  }

  private def buildUri(location: PageLocation): String = s"${baseUri}${location.sectionPagePath}/${location.path}".replaceAllLiterally("//", "/").replaceAllLiterally("//", "/")

  private def buildLocation(rawUri: String): PageLocation = {
    val uri = rawUri.stripPrefix(baseUri)
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
