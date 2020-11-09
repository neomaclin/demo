name := "demo"

version := "0.1"

scalaVersion := "2.13.3"

/*
  Project dependencies
 */
libraryDependencies ++= Seq(
  "org.springframework.boot" % "spring-boot-starter-web" % "2.3.5.RELEASE",
  "org.springframework.boot" % "spring-boot-configuration-processor" % "2.3.5.RELEASE"
)

libraryDependencies ++=  zio ++ cats ++ local ++ sttp
lazy val cats = {
  Seq(
    "org.typelevel" %% "kittens",
    "org.typelevel" %% "cats-effect",
    "org.typelevel" %% "cats-core",
    "org.typelevel" %% "cats-free"
  ).map(_ % "2.1.0")
}

lazy val zio = {
  val version = "1.0.1"
  Seq(
    // "dev.zio" %%% "zio" % "1.0.3",
    "dev.zio" %% "zio" % version,
    "dev.zio" %% "zio-logging" % "0.4.0",
    "dev.zio" %% "zio-logging-slf4j" % "0.4.0",
    "dev.zio" %% "zio-interop-cats" % "2.1.4.0"
  )
}

lazy val sttp = {
  Seq(
    "com.softwaremill.sttp.tapir" %% "tapir-json-circe",
    "com.softwaremill.sttp.tapir" %% "tapir-core",
    "com.softwaremill.sttp.tapir" %% "tapir-zio",
    "com.softwaremill.sttp.tapir" %% "tapir-akka-http-server",
    "com.softwaremill.sttp.tapir" %% "tapir-sttp-client"
  ).map(_ % "0.16.15") ++
    Seq(
      "com.softwaremill.sttp.model" %% "core" % "1.1.4",
      "com.softwaremill.sttp.client" %% "core" % "2.2.5",
    ) ++
    Seq(
      "com.softwaremill.sttp.client" %% "async-http-client-backend-zio",
      "com.softwaremill.sttp.client" %% "async-http-client-backend-future",
      "com.softwaremill.sttp.client" %% "akka-http-backend",
      "com.softwaremill.sttp.client" %% "okhttp-backend"
    ).map(_ % "2.2.9")
}
lazy val local = Seq (
  "com.quasigroup.inc" %% "zio-akka" % "0.1",
  "com.quasigroup.inc" %% "endpoints" % "0.1",
)

/*
  Packaging plugin
 */

// enable the Java app packaging archetype and Ash script (for Alpine Linux, doesn't have Bash)
//enablePlugins(JavaAppPackaging, AshScriptPlugin)

// set the main entrypoint to the application that is used in startup scripts
//mainClass in Compile := Some("com.wzhi.spring.MyServiceApplication")