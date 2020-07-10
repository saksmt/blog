package dev.saksmt.blog.backend.common.openapi

import derevo.derive
import derevo.tethys.{tethysReader, tethysWriter}

import scala.annotation.nowarn

@nowarn("cat=w-flag-self-implicit")
@derive(tethysReader, tethysWriter)
case class Error(
    code: String,
    description: Option[String],
    path: Option[List[String]],
    stackTrace: Option[String]
)
