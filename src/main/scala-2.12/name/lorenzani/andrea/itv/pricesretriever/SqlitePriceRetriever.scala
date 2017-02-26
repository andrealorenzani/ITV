package name.lorenzani.andrea.itv.pricesretriever

import name.lorenzani.andrea.itv.genericreader.SqlliteReader

class SqlitePriceRetriever(sqlliteReader: SqlliteReader) extends PricesRetriever {
  override def getPricesDB: Map[String, List[Price]] = {
    sqlliteReader.readInfo()
                 .groupBy(_.sku)
                 .map { case (sku, prices) =>
                   // It should be already sorted by the query (ORDER BY)
                   (sku, prices.sortBy(_.nitems))
                 }
  }
}
