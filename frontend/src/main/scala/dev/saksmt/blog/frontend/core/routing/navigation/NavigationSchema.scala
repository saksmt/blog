package dev.saksmt.blog.frontend.core.routing.navigation

import dev.saksmt.blog.frontend.core.routing.PageLocation
import mhtml.Rx

import scala.xml.Elem

trait NavigationSchema {
  def buildLink(location: PageLocation)(link: Elem): Elem
  def initialize(): Rx[PageLocation]
}
