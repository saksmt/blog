package dev.saksmt.blog.frontend.sections.about.page

import dev.saksmt.blog.frontend.core.Page
import dev.saksmt.blog.frontend.core.dom.Renderable

class BuiltWithPage extends Page("Built with") {
  override def content: Renderable = (
    <h1>This site is built with:</h1>
    <ul>
      <li><a href="https://scala-lang.org">Scala</a></li>
      <li><a href="https://scala-js.org">Scala.js</a></li>
      <li><a href="https://github.com/OlivierBlanvillain/monadic-html">monadic-html</a></li>
      <li><a href="https://highlightjs.org/">highlight.js</a></li>
      <li><a href="https://sass-lang.com">SCSS</a></li>
      <li><a href="https://nixos.org/nix/">Nix</a></li>
      <li><a href="https://webpack.js.org">webpack</a></li>
      <li><a href="https://scala-sbt.org">SBT</a> (shame on me, but <a href="bazel.build">bazel</a> can't into scala.js and <a href="pantsbuild.org">pants</a> don't actually work at all, at least not on NixOS)</li>
    </ul>
    <h1>And brought to you by:</h1>
    <ul>
      <li><a href="https://kubernetes.io">Kubernetes</a></li>
      <li><a href="https://istio.io">Istio</a></li>
      <li><a href="https://letsencrypt.org">Let's Encrypt</a></li>
      <li><a href="https://github.com/alash3al/httpsify">Httpsify</a> (thanks to broken let's encrypt integration in Istio)</li>
      <li><a href="https://nginx.com">NGINX</a></li>
      <li><a href="https://ansible.com">Ansible</a></li>
      <li><a href="https://docker.com">Docker</a></li>
      <li>My poor VDS handling all this stuff (<a href="https://melbicom.net/">Melbicom</a>)</li>
    </ul>
    <p></p>
    <p>P.S. sources of this site can be found in my <a href="https://github.com/saksmt/blog">github</a>, there is also <a href="https://github.com/saksmt/saksmt.dev-server-cluster">repository</a> with config for the whole cluster</p>
  )
}
