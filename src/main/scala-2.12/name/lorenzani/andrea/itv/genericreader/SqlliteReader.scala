package name.lorenzani.andrea.itv.genericreader

import java.sql.{DriverManager, ResultSet}

import name.lorenzani.andrea.itv.pricesretriever.Price

import scala.util.{Failure, Success, Try}

class SqlliteReader(dbName: String, verbose: Boolean = false) {
  private val connection = Try {
    Class.forName("org.sqlite.JDBC")
    val dbPath = getClass.getResource(s"/$dbName").getFile
    val c = DriverManager.getConnection("jdbc:sqlite:" + dbPath)
    c.setAutoCommit(false)
    if (verbose) System.out.println("Opened database successfully")
    c
  }

  def readInfo(): List[Price] = {
    connection match {
      case Success(c) =>
        val rs: ResultSet = c.createStatement()
          .executeQuery("SELECT SKU, QTY, PRICE FROM PRICES ORDER BY QTY;")
        val priceIter = new Iterator[Price] {
          def hasNext = rs.next()
          def next() = Price(rs.getString("SKU"), rs.getInt("PRICE"), rs.getInt("QTY"))
        }.toStream
        val res = priceIter.toList
        c.close
        res
      case Failure(exc) =>
        println(s"Error connecting to DB: ${exc.getMessage}")
        throw exc;
    }
  }
}
