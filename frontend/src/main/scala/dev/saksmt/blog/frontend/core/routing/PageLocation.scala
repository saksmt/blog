package dev.saksmt.blog.frontend.core.routing

case class PageLocation(sectionPagePath: String, path: String)
object PageLocation {
  def Projects(childPath: String = "/"): PageLocation = PageLocation("/projects", childPath)
  def About(childPath: String = "/"): PageLocation = PageLocation("/about", childPath)
  def Blog(childPath: String = "/"): PageLocation = PageLocation("/blog", childPath)
}
