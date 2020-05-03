package dev.saksmt.blog.frontend.core.routing.navigation

import dev.saksmt.blog.frontend.core.routing.PageLocation
import mhtml.Rx
import org.scalajs.dom.Window

import scala.xml.Elem

class SimpleHashNavigationSchema(window: Window, locationBuilder: LocationBuilder)
    extends NavigationSchema {
  override def buildLink(location: PageLocation)(link: Elem): Elem =
    locationBuilder.buildLink(location, link) {
      Function.const(())
    }

  override def initialize(): Rx[PageLocation] = {
    locationBuilder.registerLocationSource(window.location.hash) { update =>
      window.onhashchange = _ => {
        update(window.location.hash)
      }
    }
  }
}
