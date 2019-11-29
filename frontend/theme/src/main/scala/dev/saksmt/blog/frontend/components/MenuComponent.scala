package dev.saksmt.blog.frontend.components

import dev.saksmt.blog.frontend.core.dom.Renderable
import dev.saksmt.blog.frontend.{Menu, PageLocation}

class MenuComponent(menu: Menu) {
  import menu.mainPage

  def build(currentLocation: Option[PageLocation]): Renderable = (
    <h1 class="menu-home">
      <a href={mainPage.page.location.fold("#")(_.href)}>{mainPage.name}</a>
    </h1>
    <menu id="navigation-menu">
      {
      menu.sections.map { it =>
        val selectedClass =
          if (currentLocation.map(_.sectionPagePath) == it.page.location.map(_.sectionPagePath)) {
            "menu-selected"
          } else {
            ""
          }

        <li>
          <a href={it.page.location.fold("#")(_.href)} class={selectedClass}>{it.name}</a>
        </li>
      }
      }
    </menu>
    <hr/>
    <menu id="contacts-menu">
      <li>
        <a href="https://github.com/saksmt" class="github"></a>
      </li>
      <li>
        <a href="https://linkedin.com/in/saksmt" class="linkedin"></a>
      </li>
      <li>
        <a href="https://t.me/saksmt" class="telegram"></a>
      </li>
    </menu>
  )
}
