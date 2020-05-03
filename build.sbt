lazy val root = (project in file("."))
  .aggregate(
    shared,
    frontend,
    backend
  )
  .settings(
    name := "blog-root"
  )

lazy val shared = project
lazy val frontend = project
lazy val backend = project
