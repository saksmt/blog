package dev.saksmt.blog.frontend.core.routing.navigation

import dev.saksmt.blog.frontend.core.routing.PageLocation
import mhtml.{Rx, Var}
import org.scalajs.dom.html.Input
import org.scalajs.dom.{Node, Window}
import org.scalajs.dom.raw.Event

import scala.xml.{Elem, UnprefixedAttribute}

class LinkClicked private[navigation] (
    val uri: String,
    val preventDefault: () => Unit,
    val stopPropagation: () => Unit,
    val updateLocation: () => Unit
)

trait LocationBuilder {
  def registerLocationSource(initialUri: String)(source: (String => Unit) => Unit): Rx[PageLocation]

  def buildLink(location: PageLocation, link: Elem)(onClick: LinkClicked => Unit): Elem
}

class LocationBuilderImpl(window: Window, baseUri: String) extends LocationBuilder {
  private lazy val menuToggle = window.document.querySelector("#menu-toggle").asInstanceOf[Input]
  private val currentLocation = Var(Option.empty[PageLocation])

  override def registerLocationSource(
      initialUri: String
  )(source: (String => Unit) => Unit): Rx[PageLocation] = {
    source(newUri => updateLocation(buildLocation(newUri)))

    val initialLocation = buildLocation(initialUri)
    currentLocation := Some(initialLocation)

    currentLocation.map(_.getOrElse(initialLocation))
  }

  override def buildLink(location: PageLocation, link: Elem)(onClick: LinkClicked => Unit): Elem = {
    val uri = buildUri(location)
    Elem(
      link.prefix,
      link.label,
      UnprefixedAttribute(
        "href",
        uri,
        UnprefixedAttribute(
          "mhtml-onmount",
          (node: Node) => {
            node.addEventListener("click", (e: Event) => {
              onClick(
                new LinkClicked(
                  uri,
                  e.preventDefault,
                  e.stopPropagation,
                  () => updateLocation(location)
                )
              )

              true
            })
          },
          link.attributes1
        )
      ),
      link.scope,
      link.minimizeEmpty,
      link.child: _*
    )
  }

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

  private def buildUri(location: PageLocation): String =
    s"${baseUri}${location.sectionPagePath}/${location.path}"
      .replace("//", "/")
      .replace("//", "/")

  private def updateLocation(newLocation: PageLocation): Unit = {
    menuToggle.checked = false
    currentLocation := Some(newLocation)
  }
}
