package dev.saksmt.blog.frontend.wiring.impl

import dev.saksmt.blog.frontend.core.routing.navigation.{
  HistoryApiNavigationSchema,
  LocationBuilderImpl,
  NavigationSchema,
  SimpleHashNavigationSchema
}
import dev.saksmt.blog.frontend.wiring.{ConfigModule, DomModule, NavigationModule}
import com.softwaremill.macwire.wire

import scala.annotation.unused

trait NavigationModuleImpl extends NavigationModule { this: DomModule with ConfigModule =>
  override val navigationSchema: NavigationSchema = {
    scribe.info(s"Using $config as navigation config")
    scribe.info(s"routingType=${config.routingType}")
    scribe.info(s"baseUri=${config.baseUri}")

    @unused val baseUri = config.baseUri
    @unused val locationBuilder = wire[LocationBuilderImpl]

    if (config.routingType == "hash") {
      wire[SimpleHashNavigationSchema]
    } else {
      wire[HistoryApiNavigationSchema]
    }
  }
}
