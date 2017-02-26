package name.lorenzani.andrea.itv.pricesretriever

import name.lorenzani.andrea.itv.genericreader.{SqlliteReader, TextReader}
import name.lorenzani.andrea.itv.utils.Config

object PricesRetrieverFactory {
  val getPricesRetriever = getTextRetriever

  private def getTextRetriever = {
    val reader = new TextReader(Config.textfile)
    new TxtPriceRetriever(reader)
  }

  private def getSqliteRetriever = {
    val reader = new SqlliteReader(Config.dbfile)
    new SqlitePriceRetriever(reader)
  }
}
