package dev.saksmt.blog.frontend.wiring.impl

import dev.saksmt.blog.frontend.wiring.DomModule
import org.scalajs.dom.Window

trait DomModuleImpl extends DomModule {
  override val window: Window = org.scalajs.dom.window
  override val cookies: Map[String, String] = org.scalajs.dom.document.cookie
    .split("; ")
    .toSeq
    .map(cookie => {
      val cookieName = cookie.takeWhile(_ != '=')
      val cookieValue = cookie.dropWhile(_ != '=').tail

      cookieName -> cookieValue
    })
    .toMap
}
