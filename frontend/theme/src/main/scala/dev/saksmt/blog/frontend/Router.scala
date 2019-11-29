package dev.saksmt.blog.frontend

class Router(menu: Menu, navigator: PageLocation => Page) {
  private val staticPageIndex = (
    menu.mainPage.page.location.map(_.href) -> menu.mainPage.page ::
      menu.sections.map { it =>
        it.page.location.map(_.href) -> it.page
      }.toList
  ).collect {
    case (Some(location), page) => location -> page
  }.toMap

  def route(path: String): Page = {
    staticPageIndex.get(path).fold({
      val location = buildPageLocation(path)
      val page = navigator(location)
      scribe.info(s"Navigating to dynamic page ${page.location} (originally: $location) (title = ${page.title})")
    })({ page =>
      scribe.info(s"Navigating predefined to ${page.location} (title = ${page.title})")
    })
    staticPageIndex.getOrElse(path, navigator(buildPageLocation(path)))
  }

  private def buildPageLocation(fullLocation: String): PageLocation = fullLocation match {
    case pageLocationRegex(section, restOfUrl) => PageLocation(section, restOfUrl)
    case it =>
      scribe.error(s"Failed to parse path: $fullLocation, match result: $it")
      throw new Exception(s"Super strange path: $fullLocation, could not split it...")
  }

  private val pageLocationRegex = "^(/?.*?)/?(.*)$".r
}
