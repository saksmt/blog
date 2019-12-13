package dev.saksmt.blog.frontend.config

import scala.scalajs.js

@js.native
trait AppConfig extends js.Object {
  def baseUri: String
  def routingType: String
}
