package name.lorenzani.andrea.itv.genericreader

import org.scalatest.FlatSpec

class SqlliteReaderTest extends FlatSpec {

  "SqlliteReader" should "be able to open the database" in {
    val reader = new SqlliteReader("prices.sql")
    assert(reader.readInfo().size > 0)
  }

  it should "throw an error if the database is not the right one" in {
    val reader = new SqlliteReader("notexisting.sql")
    assertThrows[Exception](reader.readInfo())
  }

  it should "be able to read the database" in {
    val reader = new SqlliteReader("prices.sql")
    assert(reader.readInfo().map(x => x.sku).toSet == Set("A", "B", "C", "D"))
  }

}
