package dev.saksmt.blog.frontend.sections.about.page

import dev.saksmt.blog.frontend.core.Page
import dev.saksmt.blog.frontend.core.dom.Renderable
import dev.saksmt.blog.frontend.core.routing.PageLocation
import dev.saksmt.blog.frontend.core.routing.navigation.NavigationSchema

class AboutPage(navigationSchema: NavigationSchema) extends Page("About") {
  override val content: Renderable = (
    <h1>About</h1>
    <p>Hi, I'm Kirill Saksin</p>
    <p></p>
    <p>Currently I'm senior scala developer at <a href="https://tinkoff.ru">Tinkoff</a></p>
    <p></p>
    <p>I professionally code in:</p>
    <ul>
      <li>Scala</li>
      <li>Kotlin</li>
      <li>Java</li>
      <li>Bash</li>
      <li>Markdown (I'm senior developer after all)</li>
    </ul>
    <p>Previously I've coded in:</p>
    <ul>
      <li>PHP</li>
      <li>JS/HTML/CSS</li>
    </ul>
    <p>Also in my free time I do some strange things with:</p>
    <ul>
      <li>Linux (I'm proficient user - 3 years of gentoo don't let forget about that)</li>
      <li>Docker/Kube/Istio/Nginx/Other DevOps stuff</li>
      <li>Haskell</li>
      <li>C/C++</li>
      <li><a href="https://nixos.org/nix/">Nix</a> (yea, that's a language, checkout the link!)</li>
      <li>Lua (hate it, but <a href="https://awesomewm.org/">my window manager</a> is written in it, so checkout projects page, maybe I've finally managed to finish my very own window manager)</li>
      <li>Python (totally hate it too, but sometimes need to fix something in <a href="https://home-assistant.io">homeassistant</a>)</li>
    </ul>
    <p></p>
    <p>If you need any proofs about lists above you should check:</p>
    <ul>
      <li>
    {
      navigationSchema.buildLink(PageLocation.About("/built-with")) {
        <a>tools</a>
      }
    } this site is build with
      </li>
      <li>my 
    {
      navigationSchema.buildLink(PageLocation.Projects()) {
        <a>projects</a>
      }
    }
      </li>
    </ul>
  )
}
