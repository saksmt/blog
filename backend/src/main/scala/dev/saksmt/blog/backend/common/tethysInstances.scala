package dev.saksmt.blog.backend.common

import java.time.Instant
import java.util.UUID

import tethys.{JsonReader, JsonWriter}

object tethysInstances {
  implicit val tethysJson_instantWriter: JsonWriter[Instant] =
    JsonWriter[String].contramap(_.toString)
  implicit val tethysJson_instantReader: JsonReader[Instant] = JsonReader[String].map(Instant.parse)

  implicit val tethysJson__uuidReader: JsonReader[UUID] = JsonReader[String].map(UUID.fromString)
  implicit val tethysJson__uuidWriter: JsonWriter[UUID] = JsonWriter[String].contramap(_.toString)
}
