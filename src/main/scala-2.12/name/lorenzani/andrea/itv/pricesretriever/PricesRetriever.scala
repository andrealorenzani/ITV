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