package dev.saksmt.blog.backend.common

import sttp.tapir.Schema
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

  protected def derive[C[_]](implicit instance: C[ValueType]): C[Self#Type] = lifterF[C].lift
}

trait JsonInstances { this: TagIntrospection =>
  implicit def taggedIdJsonReader(
      implicit rawReader: JsonReader[ValueType]
  ): JsonReader[Self#Type] = derive[JsonReader]
  implicit def taggedIdJsonWriter(
      implicit rawReader: JsonWriter[ValueType]
  ): JsonWriter[Self#Type] = derive[JsonWriter]
}

//trait Tapir { this: TagIntrospection =>
//  implicit def schema(implicit schema: Schema[ValueType]): Schema[Self#Type] = derive[Schema]
//}
