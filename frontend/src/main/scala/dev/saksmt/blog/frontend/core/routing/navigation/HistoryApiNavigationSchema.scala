package dev.saksmt.blog.frontend.core.routing.navigation

import dev.saksmt.blog.frontend.core.routing.PageLocation
import mhtml.Rx
import org.scalajs.dom.raw.Event
import org.scalajs.dom.Window

import scala.xml.Elem

class HistoryApiNavigationSchema(window: Window, locationBuilder: LocationBuilder)
    extends NavigationSchema {
  override def buildLink(location: PageLocation)(link: Elem): Elem =
    locationBuilder.buildLink(location, link) { e =>
      window.history.pushState(null, "", e.uri)

      e.updateLocation()
      e.preventDefault()
      e.stopPropagation()
    }

  override def initialize(): Rx[PageLocation] = {
    def uri: String = window.location.pathname + window.location.search

    locationBuilder.registerLocationSource(uri) { update =>
      window.addEventListener("popstate", (_: Event) => {
        update(uri)
      })
    }
  }
}
