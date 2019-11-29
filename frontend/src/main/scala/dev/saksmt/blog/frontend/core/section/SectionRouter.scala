package dev.saksmt.blog.frontend.core.section

import dev.saksmt.blog.frontend.core.Page
import dev.saksmt.blog.frontend.core.routing.PageLocation
import dev.saksmt.blog.frontend.core.routing.matching.dsl._

class SectionRouter(sections: Seq[Section]) {
  val router: PartialFunction[PageLocation, Page] = {
    sections.foldLeft(PartialFunction.empty[PageLocation, Page]) { case (result, nextSection) =>
      val sectionPath = nextSection.path
      val nextRoute = nextSection.route
      result.orElse {
        case `sectionPath` -> route if nextRoute.isDefinedAt(route) => nextRoute(route)
      }
    }
  }
}
