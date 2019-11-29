lazy val shared = project in file("shared")
lazy val frontend = project in file("frontend")
lazy val backend = project in file("backend")
lazy val root = (project in file("."))
  .aggregate(
    shared,
    frontend,
    backend
  )

