package dev.saksmt.blog.frontend

import dev.saksmt.blog.frontend.config.AppConfig
import dev.saksmt.blog.frontend.highlight.Highlight

import scala.scalajs.js.annotation.JSExportTopLevel
import org.scalajs.dom._

import scala.annotation.unused

object Main {

  @JSExportTopLevel("runScalaApp")
  def main(@unused highlight: Highlight, config: AppConfig): Unit =
    document.addEventListener("DOMContentLoaded", (_: Event) => runApp(config))

  private def runApp(config: AppConfig): Unit = {
    scribe.info("Starting app")
    new AppModule(config).run()
  }
}
