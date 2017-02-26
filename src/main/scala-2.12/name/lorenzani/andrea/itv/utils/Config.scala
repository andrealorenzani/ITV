package name.lorenzani.andrea.itv.utils

import com.typesafe.config.ConfigFactory

object Config {
  private val conf = ConfigFactory.load()
  lazy val dbfile = conf.getString("dbfile")
  lazy val textfile = conf.getString("textfile")
}
