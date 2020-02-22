package dev.saksmt.blog.backend.common

import supertagged._
import tethys.{JsonReader, JsonWriter}

trait Tagged[T] extends TaggedType[T] with TagIntrospection {
  override type ValueType = T
  override type Self = this.type

  override protected def tagged(raw: T): Type = this(raw)
  override protected def untagged(tagged: Type): T = this.raw(tagged)
}

trait TagIntrospection {
  type ValueType
  type Self <: TaggedType[ValueType]

  protected def tagged(raw: ValueType): Self#Type
  protected def untagged(tagged: Self#Type): ValueType
}

trait JsonInstances { this: TagIntrospection =>
  implicit def taggedIdJsonReader(implicit rawReader: JsonReader[ValueType]): JsonReader[Self#Type] = lifterF[JsonReader].lift
  implicit def taggedIdJsonWriter(implicit rawReader: JsonWriter[ValueType]): JsonWriter[Self#Type] = lifterF[JsonWriter].lift
}
