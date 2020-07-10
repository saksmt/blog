package dev.saksmt.blog.backend.common.openapi

import sttp.tapir.docs.openapi.TapirOpenAPIDocs
import sttp.tapir.generic.Derived
import sttp.tapir.json.circe.TapirJsonTethys
import sttp.tapir.openapi.circe.yaml.TapirOpenAPICirceYaml
import sttp.tapir.{Tapir, TapirAliases}

object tapir
    extends Tapir
    with TapirAliases
    with TapirOpenAPIDocs
    with TapirOpenAPICirceYaml
    with TapirJsonTethys {

  type Validator[T] = sttp.tapir.Validator[T]
  val Validator: sttp.tapir.Validator.type = sttp.tapir.Validator

  def mkSchema[T](schemaBuilder: Schema[T] => Schema[T] = (it: Schema[T]) => it)(
      implicit derived: Derived[Schema[T]]
  ): Schema[T] = schemaBuilder(derived.value)

  def api(name: String): Endpoint[Unit, List[Error], Unit, Nothing] =
    endpoint.name(name).errorOut(jsonBody[List[Error]])

  implicit final class SchemaSummon(val schema: sttp.tapir.Schema.type) extends AnyVal {
    def summon[T : Schema]: Schema[T] = implicitly[Schema[T]]
  }
}
