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

package name.lorenzani.andrea.itv

import java.util.Scanner

import name.lorenzani.andrea.itv.checkout.CheckoutSystem
import name.lorenzani.andrea.itv.pricesretriever.PricesRetrieverFactory

import scala.util.control.Breaks._

object CheckoutKata extends App {
  val pricesDB = PricesRetrieverFactory.getPricesRetriever.getPricesDB
  if(args.length>0){
    println("Thank you for buying in our supermarket!!!")
    println("You bought:")
    args.foreach(println(_))
    val checkout = new CheckoutSystem(pricesDB)
    checkout.addElements(args.toList.map(_.toUpperCase))
    println(s"Total price: ${checkout.getPrice}")
  }
  else {
    println("Interactive mode - Insert your SKU in your basket")
    println("You can add one by one by adding a SKU followed by return")
    println("Or multiple separating them by a space and pressing return at the end")
    println("You can also add a '-' in front of a SKU for removing it from the basket")
    println("Press dot '.' and return to get the total")
    val checkout = new CheckoutSystem(pricesDB)
    val keyBoard: Scanner = new Scanner(System.in)
    breakable {
      while (true) {
        print("[ ")
        checkout.getElements.foreach(sku => print(s"$sku "))
        print("] $> ")
        val elemToAdd = keyBoard.nextLine
        if (elemToAdd.equals(".")) {
          break
        }
        val elements: List[String] = elemToAdd.split(" ").toList
        checkout.addElements(elements.filterNot( x => x.charAt(0)=='-').map(_.toUpperCase))
        checkout.removeElements(elements.filter( x => x.charAt(0)=='-').map(_.substring(1).toUpperCase))
      }
    }
    println(s"Total price: ${checkout.getPrice}")
  }
}
