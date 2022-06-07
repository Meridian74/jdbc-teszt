ASSIGNED TASK:
--------------
A company developing a webshop needs an app to handle their orders. A file contains the details of the products purchased and their associated transaction ID (transactionId). There can be several products in one transaction!
For products, the file must contain the following information:
- transactionId - long
- purchaseTime - date
- itemId - integer
- quantity - short
- itemName - string
- description - string

The unit stores the unit price of products in a database per product ID (itemId).

The application must read the file containing the product data and then print the totals for each transaction in a separate file.
Purchased products must also be recorded in a transaction table, so all purchases must be saved in the database as well.
You can choose the format of the files to be created (csv, JSON, etc.) and the database used (MySQL, Oracle, etc.).

-------------------------
SOLUTION:
---------

I used only simple Java SE 15 with JDBC. 
Boiler plate codes have been eliminated by using Lombok third party library.
Checking this solution yue can use own database server, but you have to create a database with name "jdbc_teszt".
Or you can use docker (docker desktop), using attached testDB.bat script file.

Basic information for using postgreSql DB:
- Database Server TCP port number: 5436 (if you use attached docker script)
- Database name: "jdbc_teszt"
- Database user name: "user"
- Database user password: "abc123"
---------------------------------
