package dev.saksmt.blog.backend.publicApi

import dev.saksmt.blog.backend.common.{JsonInstances, Tagged}

package object model {
  object BlogPostTitle extends Tagged[String] with JsonInstances
  type BlogPostTitle = BlogPostTitle.Type

  object ImageLink extends Tagged[String] with JsonInstances
  type ImageLink = ImageLink.Type

  object Keyword extends Tagged[String] with JsonInstances
  type Keyword = Keyword.Type

  object Tag extends Tagged[String] with JsonInstances
  type Tag = Keyword.Type
}
