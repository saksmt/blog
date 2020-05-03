package dev.saksmt.blog.frontend.highlight

import org.scalajs.dom.Element

import scala.annotation.nowarn
import scalajs.js

@js.native
trait Highlight extends js.Object {
  def highlight(
      block: Element,
      @nowarn lang: Option[String] = js.native,
      @nowarn tabSize: Option[Int] = js.native
  ): Unit
}
