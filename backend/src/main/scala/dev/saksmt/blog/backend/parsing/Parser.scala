package dev.saksmt.blog.backend.parsing

import dev.saksmt.blog.backend.publicApi.model.BlogPost
import monix.eval.Task

trait Parser {
  def parsePost(raw: String): Task[BlogPost]
}
