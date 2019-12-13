package dev.saksmt.blog.frontend

import com.github.ghik.silencer.silent
import dev.saksmt.blog.frontend.config.AppConfig
import dev.saksmt.blog.frontend.highlight.Highlight

import scala.scalajs.js.annotation.JSExportTopLevel
import org.scalajs.dom._

object Main {

  @JSExportTopLevel("runScalaApp")
  def main(@silent highlight: Highlight, config: AppConfig): Unit = document.addEventListener("DOMContentLoaded", (_: Event) => runApp(config))

  private def runApp(config: AppConfig): Unit = {
    scribe.info("Starting app")
    new AppModule(config).run()
  }
}