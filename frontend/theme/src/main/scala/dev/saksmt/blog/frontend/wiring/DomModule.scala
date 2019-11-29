package dev.saksmt.blog.frontend.wiring

import org.scalajs.dom.{Document, Window}

trait DomModule {
  def window: Window
  final lazy val document: Document = window.document
}
