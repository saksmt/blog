package dev.saksmt.blog.frontend.core.section

import dev.saksmt.blog.frontend.core.routing.matching.dsl.RoutingDSL

abstract class AbstractSection(override val name: String, override val path: String)
    extends Section
    with RoutingDSL
