import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

import com.typesafe.sbt.SbtGit.git
import sbt.Keys.version
import sbt._

object BuildInfo {
  val buildNumber: Option[String] = sys.env.get("GITHUB_RUN_NUMBER")

  val generator = Def.task {
    val commitDate = git.gitHeadCommitDate.value
      .map(OffsetDateTime.parse(_, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ")))
      .map(_.toInstant.toEpochMilli.toString + "L")

    val tagList = git.gitCurrentTags.value
      .map(it => "\"" + it + "\" ::")
      .mkString("") + " Nil"

    val gitBranch = Some(git.gitCurrentBranch.value).filter(_.nonEmpty)

    s"""
       | package dev.saksmt.blog
       | 
       | object buildInfo {
       |   val version = "${version.value}"
       |   val buildNumber: Option[Int] = ${buildNumber.asStringRaw}
       |   val buildTimestamp = ${System.currentTimeMillis()}L
       |   val commitTimestamp: Option[Long] = ${commitDate.asStringRaw}
       |   val commitHash: Option[String] = ${git.gitHeadCommit.value.asString}
       |   val gitBranch: Option[String] = ${gitBranch.asString}
       |   val gitTags: List[String] = $tagList
       | }
       |""".stripMargin
  }

  implicit private class OptionSerialization(val opt: Option[String]) extends AnyVal {
    def asString: String = opt.map("\"%s\"".format(_)).asStringRaw
    def asStringRaw: String = opt.map("Some(%s)".format(_)).getOrElse("None")
  }
}
