package dev.saksmt.blog.backend.publicApi.model

import org.manatki.derevo.derive
import org.manatki.derevo.tethysInstances.{tethysReader, tethysWriter}
import tethys.JsonReader

@derive(tethysReader, tethysWriter)
case class BlogPost(title: BlogPostTitle, content: String, tags: Seq[Tag], keywords: Seq[Keyword], images: Seq[ImageLink]) {
  implicitly[JsonReader[BlogPostTitle]]
}
@derive(tethysWriter)
case class Kek(lel: String)
case class Metadata(tags: Seq[Tag], keywords: Seq[Keyword], originalFileName: String)
