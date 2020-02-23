package dev.saksmt.blog.frontend.wiring.impl

import com.github.ghik.silencer.silent
import dev.saksmt.blog.frontend.core.routing.navigation.{HistoryApiNavigationSchema, LocationBuilderImpl, NavigationSchema, SimpleHashNavigationSchema}
import dev.saksmt.blog.frontend.wiring.{ConfigModule, DomModule, NavigationModule}
import com.softwaremill.macwire.wire

@silent
trait NavigationModuleImpl extends NavigationModule { this: DomModule with ConfigModule =>
  override val navigationSchema: NavigationSchema = {
    scribe.info(s"Using $config as navigation config")
    scribe.info(s"routingType=${config.routingType}")
    scribe.info(s"baseUri=${config.baseUri}")

    val baseUri = config.baseUri
    val locationBuilder = wire[LocationBuilderImpl]

    if (config.routingType == "hash") {
      wire[SimpleHashNavigationSchema]
    } else {
      wire[HistoryApiNavigationSchema]
    }
  }
}
