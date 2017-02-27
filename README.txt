The project is an SBT project (as you can see from the build.sbt).
As usual to launch use
   sbt "run [args]"

[args] is the list of element bought for which you want the total
Without any argument the application start in interactive mode. In
that mode you can add and remove SKU interactively.

To run tests use
   sbt test

Please consider that this solution comes in two fashions for the db:
 -- a txt version with data in the format
      SKU|Price 1 item|#items and price separated by comma for the offers
 -- a Sqlite database of prices with one table
    CREATE TABLE IF NOT EXISTS PRICES
 	(ID 	INTEGER PRIMARY KEY AUTOINCREMENT,
     SKU	TEXT NOT NULL,
     PRICE	INT NOT NULL,
 	 QTY	INT NOT NULL);

To swap from sql to txt is enough to change the method getPricesRetriever
in the PricesRetrieverFactory (I could have put it in the config... :/)
Databases and config files are in the resources directory. Feel free to
 change them

I feel the test suite is a bit poor, and the error handling as well.
But the "This needs to be completed as soon as possible" in the email
has stressed me a lot, and I have also several tests to do.
I am available for defending my choices whenever you want.
Thank you for the time you will spend on this.
Regards,
 -- Andrea