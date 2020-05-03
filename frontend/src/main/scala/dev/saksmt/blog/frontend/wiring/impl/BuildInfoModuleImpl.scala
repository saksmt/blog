package dev.saksmt.blog.frontend.wiring.impl

import dev.saksmt.blog.buildInfo
import dev.saksmt.blog.frontend.core.dom.Renderable
import dev.saksmt.blog.frontend.wiring.{BuildInfoModule, ConfigModule}

import scala.scalajs.js.Date
import scala.xml.{Node, Text}

trait BuildInfoModuleImpl extends BuildInfoModule { this: ConfigModule =>
  private def commitLink(text: => String): Option[Node] =
    buildInfo.commitHash.map(it => <a href={s"https://github.com/saksmt/blog/tree/$it"}>{text}</a>)

  override def footerContent: Renderable =
    <div>
    {
      if (isDeveloper) {
        val elements = Seq(
          Some(Seq(Text("v" + buildInfo.version))),
          commitLink(buildInfo.commitHash.get).map(it => Seq(Text("hash: "), it)),
          Some(Seq(Text("built at: " + new Date(buildInfo.buildTimestamp.toDouble).toISOString()))),
          buildInfo.buildNumber.map("build number: " + _).map(Text.apply).map(Seq(_))
        ).flatten.toList

        (elements.head :: elements.tail.map(it => Text("; ") :: it.toList)).flatten
      } else {
        Seq(
          Text(s"${new Date().getFullYear()} // "),
          commitLink("v" + buildInfo.version).getOrElse(Text("v" + buildInfo.version))
        )
      }
    }</div>
}
