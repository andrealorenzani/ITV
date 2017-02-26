package name.lorenzani.andrea.itv.genericreader

import scala.io.Source

class TextReader(file: String) {

  private val bufferedSource = Source.fromInputStream(getClass.getResourceAsStream(s"/$file"))
  def readInfo(): Iterator[String] = bufferedSource.getLines()
  def close(): Unit = bufferedSource.close()
}
