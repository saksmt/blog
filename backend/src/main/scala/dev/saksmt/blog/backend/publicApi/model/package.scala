package dev.saksmt.blog.backend.publicApi

import dev.saksmt.blog.backend.common.{JsonInstances, Tagged}

package object model {
  import dev.saksmt.blog.backend.common.openapi.tapir._

  object BlogPostTitle extends Tagged[String] with JsonInstances {
    implicit val schema: Schema[Type] = derive[Schema].description("blog post title")
    implicit val validator: Validator[Type] = derive[Validator]
  }
  type BlogPostTitle = BlogPostTitle.Type

  object Keyword extends Tagged[String] with JsonInstances {
    implicit val schema: Schema[Type] = derive[Schema].description("search keyword")
    implicit val validator: Validator[Type] = derive[Validator]
  }
  type Keyword = Keyword.Type

  object Tag extends Tagged[String] with JsonInstances {
    implicit val schema: Schema[Type] = derive[Schema].description("blog post tag")
    implicit val validator: Validator[Type] = derive[Validator]
  }
  type Tag = Keyword.Type

  object BlogPostSlug extends Tagged[String] with JsonInstances {
    implicit val schema: Schema[Type] =
      derive[Schema].description("human readable uri part identifying blog post")
    implicit val validator: Validator[Type] = derive[Validator]
  }
  type BlogPostSlug = BlogPostSlug.Type
}
