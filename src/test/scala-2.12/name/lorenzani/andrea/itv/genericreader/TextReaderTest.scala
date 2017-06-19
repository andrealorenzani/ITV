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

class TextReaderTest extends FlatSpec {
  "The TextReader" should "be able to read a file and return its content" in {
    val reader = new TextReader("testfiles/filewithnoprices")
    assert(reader.readInfo().next().equals("The quantity of civilization is measured by the quality of imagination."))
  }

  it should "be able to open an empty file" in {
    val reader = new TextReader("testfiles/emptyfile")
    assert(!reader.readInfo().hasNext)
  }

  it should "throw an error if it tries to open a non existing file" in {
    val reader = new TextReader("notexisting")
    assertThrows[Exception](reader.readInfo())
  }

  it should "be able to read the database" in {
    val reader = new TextReader("testfiles/prices.txt")
    assert(reader.readInfo().map(x => x.split("|")(0)).toSet == Set("A", "B", "C", "D"))
  }
}
