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
import org.scalatest.{Matchers, FlatSpec}

class TxtPriceRetrieverTest extends FlatSpec with Matchers {
  val okTextReader = new TextReader("testfiles/prices.txt")
  val notworkingTextReader = new TextReader("testfiles/notexisting")
  val notrightformat = new TextReader("testfiles/filewithnoprices")

  "TxtPriceRetriever" should "generate the right map of values" in {
    val preader = new TxtPriceRetriever(okTextReader)
    val resMap = preader.getPricesDB
    resMap("A").size shouldBe 2
    assert(resMap("A").map(_.price).toSet == Set(50, 130))
    assert(resMap("A").map(_.nitems).toSet == Set(1, 3))
    resMap("B").size shouldBe 2
    assert(resMap("B").map(_.price).toSet == Set(30, 45))
    assert(resMap("B").map(_.nitems).toSet == Set(1, 2))
    resMap("C").size shouldBe 1
    assert(resMap("C").map(_.price).toSet == Set(20))
    assert(resMap("C").map(_.nitems).toSet == Set(1))
    resMap("D").size shouldBe  1
    assert(resMap("D").map(_.price).toSet == Set(15))
    assert(resMap("D").map(_.nitems).toSet == Set(1))
    resMap.size shouldBe 4
  }

  it should "throws exception with a not working reader" in {
    val preader = new TxtPriceRetriever(notworkingTextReader)
    assertThrows[Exception](preader.getPricesDB)
  }

  it should "return an empty map with a reader with a wrong text format" in {
    val preader = new TxtPriceRetriever(notrightformat)
    preader.getPricesDB shouldBe Map()
  }
}
