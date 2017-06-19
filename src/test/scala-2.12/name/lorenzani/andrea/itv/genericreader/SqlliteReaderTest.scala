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
