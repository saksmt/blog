package dev.saksmt.blog.frontend.components.menu

import dev.saksmt.blog.frontend.core.dom.Renderable
import dev.saksmt.blog.frontend.core.routing.PageLocation
import dev.saksmt.blog.frontend.core.routing.navigation.NavigationSchema

class MenuComponent(menu: Menu, navigationSchema: NavigationSchema) {
  import menu.mainPage

  def build(currentLocation: PageLocation): Renderable = (
    <h1 class="menu-home">
      {navigationSchema.buildLink(mainPage.location) {
        <a>{mainPage.name}</a>
      }}
    </h1>
    <menu id="navigation-menu">
      {
      menu.sections.map { it =>
        val selectedClass =
          if (currentLocation.sectionPagePath == it.location.sectionPagePath) {
            "menu-selected"
          } else {
            ""
          }

        <li>
          {navigationSchema.buildLink(it.location) {
            <a class={selectedClass}>{it.name}</a>
          }}
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
