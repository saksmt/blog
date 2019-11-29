package dev.saksmt.blog.frontend.highlight

import org.scalajs.dom.Element

import scalajs.js

@js.native
trait Highlight extends js.Object {
  def highlight(block: Element, lang: Option[String] = js.native, tabSize: Option[Int] = js.native): Unit
}
