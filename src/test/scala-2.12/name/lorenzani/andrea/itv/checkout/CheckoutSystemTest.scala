package name.lorenzani.andrea.itv.checkout

import name.lorenzani.andrea.itv.genericreader.SqlliteReader
import name.lorenzani.andrea.itv.pricesretriever.SqlitePriceRetriever
import org.scalatest.FlatSpec

class CheckoutSystemTest extends FlatSpec {
  def validPricesDB = new SqlitePriceRetriever(new SqlliteReader("prices.sql")).getPricesDB
  def emptyPricesDb = Map()

  "CheckoutSystem in SQL" should "return zero with no element in the basket" in {
    val basket = new CheckoutSystem(validPricesDB)
    assert(basket.getPrice == 0)
  }

  it should "return the prices for single elements with one element per type in the basket" in {
    val basket = new CheckoutSystem(validPricesDB)
    basket.addElements(List("A","B","C","D"))
    assert(basket.getPrice == 115)
  }

  it should "return the right value for an offer" in {
    val basket = new CheckoutSystem(validPricesDB)
    basket.addElement("A")
    basket.addElement("A")
    basket.addElement("A")
    basket.addElement("A")
    assert(basket.getPrice == 180)
  }

  it should "return the right value for an offer and for elements removed" in {
    val basket = new CheckoutSystem(validPricesDB)
    basket.addElement("A")
    basket.addElement("A")
    basket.addElement("A")
    basket.removeElement("A")
    assert(basket.getPrice == 100)
  }

}
