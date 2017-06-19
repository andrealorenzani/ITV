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

import name.lorenzani.andrea.itv.genericreader.SqlliteReader
import org.scalatest.FlatSpec

class SqlitePriceRetrieverTest extends FlatSpec {
  val okReader = new SqlliteReader("prices.sql")
  val notworkingReader = new SqlliteReader("notexisting.sql")

  "SqlitePriceRetriever" should "generate the right map of values" in {
    val preader = new SqlitePriceRetriever(okReader)
    val resMap = preader.getPricesDB
    assert(resMap("A").size == 2)
    assert(resMap("A").map(_.price).toSet == Set(50, 130))
    assert(resMap("A").map(_.nitems).toSet == Set(1, 3))
    assert(resMap("B").size == 2)
    assert(resMap("B").map(_.price).toSet == Set(30, 45))
    assert(resMap("B").map(_.nitems).toSet == Set(1, 2))
    assert(resMap("C").size == 1)
    assert(resMap("C").map(_.price).toSet == Set(20))
    assert(resMap("C").map(_.nitems).toSet == Set(1))
    assert(resMap("D").size == 1)
    assert(resMap("D").map(_.price).toSet == Set(15))
    assert(resMap("D").map(_.nitems).toSet == Set(1))
    assert(resMap.size == 4)
  }

  it should "throws exception with a not working reader" in {
    val preader = new SqlitePriceRetriever(notworkingReader)
    assertThrows[Exception](preader.getPricesDB)
  }
}
