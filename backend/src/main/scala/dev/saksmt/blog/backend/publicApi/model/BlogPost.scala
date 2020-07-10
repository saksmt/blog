package dev.saksmt.blog.backend.publicApi.model

import java.time.Instant

import derevo.derive
import derevo.tethys.{tethysReader, tethysWriter}
import dev.saksmt.blog.backend.common.tethysInstances._

import dev.saksmt.blog.backend.common.openapi.tapir
import tapir.{Schema, mkSchema}

import scala.annotation.nowarn

@nowarn("cat=w-flag-self-implicit")
@derive(tethysReader, tethysWriter)
case class BlogPost(
    title: BlogPostTitle,
    content: String,
    metadata: BlogPostMetadata
)

@nowarn("cat=w-flag-self-implicit")
@derive(tethysReader, tethysWriter)
case class BlogPostMetadata(
    tags: Seq[Tag],
    keywords: Seq[Keyword],
    slug: BlogPostSlug,
    createdAt: Instant,
    publishedAt: Option[Instant]
)

@nowarn("cat=w-flag-self-implicit")
@derive(tethysReader, tethysWriter)
case class BlogPostSearchResult(total: Int, offset: Int, items: List[BlogPostSearchResultItem])
object BlogPostSearchResult {
  implicit val schema: Schema[BlogPostSearchResultItem] = mkSchema()
}

@nowarn("cat=w-flag-self-implicit")
@derive(tethysReader, tethysWriter)
case class BlogPostSearchResultItem(title: BlogPostTitle, d: String, metadata: BlogPostMetadata)
object BlogPostSearchResultItem {
  implicit val schema: Schema[BlogPostSearchResultItem] = mkSchema()
}
