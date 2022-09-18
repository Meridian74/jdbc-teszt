package service;

import config.InitDB;

public class DataBaseInitalizer {

   private final InitDB initDB;

   public DataBaseInitalizer(InitDB initDB) {
      this.initDB = initDB;
   }

   public void createTables() {
      initDB.createItemTable();
      initDB.createItemPriceTable();
      initDB.createTransactionTable();
      initDB.createTransactionAndItemsTable();
   }


   public void initSampleData() {
      initDB.initSampleItems();
      initDB.initSampleItemPrices();
   }

}