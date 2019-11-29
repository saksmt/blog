package dev.saksmt.blog.frontend.highlight

import com.github.ghik.silencer.silent
import org.scalajs.dom.Element

import scalajs.js

@js.native
trait Highlight extends js.Object {
  def highlight(block: Element, @silent lang: Option[String] = js.native, @silent tabSize: Option[Int] = js.native): Unit
}
