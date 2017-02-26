package name.lorenzani.andrea.itv.pricesretriever

import scala.util.Try

trait PricesRetriever {
  def getPricesDB: Map[String, List[Price]]

  // Utility method that check is a String is a SKU
  // Please note: from the text I understand that a SKU is a single letter
  def isSku(str: String): Boolean = str.matches("^[a-zA-Z]$")

  // Utility method that check if a String is a valid price and to return a price
  // Please note: from the text I understand that price is an integer
  // (but if it is a Long we can easily change this)
  def isPrice(str: String): Boolean = Try{str.toInt}.isSuccess
  def getPrice(str: String) = str.toInt
}

case class Price(sku: String, price: Int, nitems: Int = 1)