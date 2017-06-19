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

import name.lorenzani.andrea.itv.genericreader.TextReader

import scala.io.Source
import scala.util.Try

class TxtPriceRetriever(fileReader: TextReader) extends PricesRetriever {
  override def getPricesDB: Map[String, List[Price]] = {
    val prices: List[List[Price]] = {
      for {
        line <- fileReader.readInfo()
        // In the input file we use the format
        // <SKU> | <single item price> | <special prices as [nitems,price]*>
        singlePrice: Array[String] = line.split("\\|")
        // At least SKU and single item price
        if singlePrice.length >= 2 && isSku(singlePrice(0)) && isPrice(singlePrice(1))
      } yield {
        List(Price(singlePrice(0), singlePrice(1).toInt)) ++
          (singlePrice.drop(2).collect { case offerCSV if checkValidSpecialOffer(offerCSV) =>
            val offer = offerCSV.split(",")
            Price(singlePrice(0), offer(1).toInt, getPrice(offer(0)))
          } sortBy (_.nitems))
      }
    }.toList
    fileReader.close()
    prices.map(skuPrices => (skuPrices.head.sku, skuPrices)).toMap
  }

  private def checkValidSpecialOffer(offerInCSV: String): Boolean = {
    // special offers are encoded as list of #items,price
    val offer: Array[String] = offerInCSV.split(",")
    offer.length == 2 && Try {
      offer(0).toInt
    }.isSuccess && isPrice(offer(1))
  }
}
