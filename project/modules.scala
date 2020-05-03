import sbt.ProjectRef

import scala.language.dynamics

object modules extends Dynamic {
  def selectDynamic(name: String): ProjectRef = ProjectRef(sbt.file("."), name)
}
