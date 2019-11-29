package dev.saksmt.blog.frontend

import com.github.ghik.silencer.silent
import dev.saksmt.blog.frontend.highlight.Highlight

import scala.scalajs.js.annotation.JSExportTopLevel
import org.scalajs.dom._

object Main {

  @JSExportTopLevel("runScalaApp")
  def main(@silent highlight: Highlight): Unit = document.addEventListener("DOMContentLoaded", (_: Event) => runApp())

  private def runApp(): Unit = {
    new AppModule().run()
  }
}