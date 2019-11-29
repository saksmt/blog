package dev.saksmt.blog.frontend

import dev.saksmt.blog.frontend.highlight.Highlight

import scala.scalajs.js.annotation.JSExportTopLevel
import org.scalajs.dom._

import scala.xml.Atom

object Main {

  @JSExportTopLevel("runScalaApp")
  def main(highlight: Highlight): Unit = document.addEventListener("DOMContentLoaded", (_: Event) => runApp())

  private def runApp(): Unit = {
    new AppModule().run()
  }
}