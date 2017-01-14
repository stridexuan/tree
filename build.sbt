organization  := "com.tree"

version       := "0.1"

scalaVersion  := "2.10.6"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

libraryDependencies ++= {
  val akkaV = "2.3.9"
  val sprayV = "1.3.3"
  Seq(
    "io.spray"            %%  "spray-can"     % sprayV,
    "io.spray"            %%  "spray-routing" % sprayV,
    "io.spray"            %%  "spray-testkit" % sprayV  % "test",
    "com.typesafe.akka"   %%  "akka-actor"    % akkaV,
    "com.typesafe.akka"   %%  "akka-testkit"  % akkaV   % "test",
    "org.specs2"          %%  "specs2-core"   % "2.3.11" % "test",
    "com.typesafe"        %     "config"     % "1.2.1",
    "com.h2database"      %   "h2"  % "1.4.192",
    "org.slf4j" % "slf4j-api" % "1.7.21",
    "org.log4s" % "log4s_2.11" % "1.3.3",
    "org.json4s"          %   "json4s-native_2.11" % "3.4.0"
  )
}

Revolver.settings
