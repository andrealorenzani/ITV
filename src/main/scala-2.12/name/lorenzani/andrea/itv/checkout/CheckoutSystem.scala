package name.lorenzani.andrea.itv.checkout

import name.lorenzani.andrea.itv.pricesretriever.Price

import scala.collection.mutable.ListBuffer

class CheckoutSystem(prices: Map[String, List[Price]]) {
  private var shopping = new ListBuffer[String]()

  def addElement(elem: String) = if (prices.contains(elem)) shopping += elem

  def addElements(elems: List[String]) = shopping ++= elems.filter(prices.contains)

  def removeElement(elem: String) = shopping -= elem

  def removeElements(elems: List[String]) = shopping --= elems

  def getElements = shopping.toList

  def getPrice: Int = getPrice(shopping.toList)

  def getPrice(basket: List[String]): Int = {
    basket.filter(prices.contains)
      .groupBy(x => x)
      .map { case (sku, occurrencies) => getPrice(sku, occurrencies.size) }
      .sum
  }

  def getPrice(sku: String, nitems: Int): Int = {
    nitems match {
      case qty if qty <= 0 => 0
      case qty =>
        val skuPrices: List[Price] = prices(sku)
        // PLEASE NOTE: the following line should always return at least the List with price of 1 element
        val bestOffer = skuPrices.filter(_.nitems <= nitems).last
        bestOffer.price * (nitems / bestOffer.nitems) + getPrice(sku, nitems % bestOffer.nitems)
    }
  }
}
