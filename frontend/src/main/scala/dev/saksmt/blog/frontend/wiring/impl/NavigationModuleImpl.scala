package dev.saksmt.blog.frontend.wiring.impl

import com.github.ghik.silencer.silent
import dev.saksmt.blog.frontend.core.routing.navigation.{HistoryApiNavigationSchema, NavigationSchema, SimpleHashNavigationSchema}
import dev.saksmt.blog.frontend.wiring.{ConfigModule, DomModule, NavigationModule}
import com.softwaremill.macwire.wire

@silent
trait NavigationModuleImpl extends NavigationModule { this: DomModule with ConfigModule =>
  override val navigationSchema: NavigationSchema = {
    scribe.info(s"Using $config as navigation config")
    scribe.info(s"routingType=${config.routingType}")
    scribe.info(s"baseUri=${config.baseUri}")
    if (config.routingType == "hash") {
      wire[SimpleHashNavigationSchema]
    } else {
      val baseUri = config.baseUri
      wire[HistoryApiNavigationSchema]
    }
  }
}
