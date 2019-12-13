package dev.saksmt.blog.frontend.wiring

import dev.saksmt.blog.frontend.config.AppConfig

trait ConfigModule {
  def config: AppConfig
}
