package dev.saksmt.blog.frontend.core.dom

import mhtml.Rx

import scala.language.implicitConversions
import scala.xml.{Atom, Node, NodeBuffer}

trait Renderable {
  def render: Rx[Node]
}
object Renderable {
  implicit def renderableFromXml(xml: Node): Renderable = new RenderableFromXml(xml)
  implicit def renderableFromXmlNodes(xml: NodeBuffer): Renderable = new RenderableFromXml(new Atom(xml))
  implicit def renderableFromText(text: String): Renderable = new RenderableFromXml(new Atom(text))

  implicit def renderableFromRxXml(xml: Rx[Node]): Renderable = new RenderableRx(xml.map(it => it : Renderable))
  implicit def renderableFromRxXmlNodes(xml: Rx[NodeBuffer]): Renderable = new RenderableRx(xml.map(it => it : Renderable))
  implicit def renderableFromRxText(text: Rx[String]): Renderable = new RenderableRx(text.map(it => it : Renderable))

  private class RenderableFromXml(xml: Node) extends Renderable {
    override val render: Rx[Node] = Rx(xml)
  }
  private class RenderableRx(rx: Rx[Renderable]) extends Renderable {
    override val render: Rx[Node] = rx.flatMap(_.render)
  }
}
