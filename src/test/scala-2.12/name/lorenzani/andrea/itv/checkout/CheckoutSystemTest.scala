/***
*   Copyright 2017 Andrea Lorenzani
*
*   Licensed under the Apache License, Version 2.0 (the "License");
*   you may not use this file except in compliance with the License.
*   You may obtain a copy of the License at
*
*       http://www.apache.org/licenses/LICENSE-2.0
*
*   Unless required by applicable law or agreed to in writing, software
*   distributed under the License is distributed on an "AS IS" BASIS,
*   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*   See the License for the specific language governing permissions and
*   limitations under the License.
*
***/

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
