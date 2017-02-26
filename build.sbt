name := "ITV_Test"

version := "1.0"

scalaVersion := "2.12.1"

mainClass in(Compile, run) := Some("name.lorenzani.andrea.itv.CheckoutKata")

libraryDependencies ++= Seq(
  "org.xerial" % "sqlite-jdbc" % "3.8.7",
  "com.typesafe" % "config" % "1.3.1",
  "org.scalatest" %% "scalatest" % "3.0.1" % "test"
)
    