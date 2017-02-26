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
