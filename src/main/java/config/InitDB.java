package config;

import model.Item;
import model.ItemPrice;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class InitDB {

   private final DataSource dataSource;

   public InitDB(DataSource dataSource) {
      this.dataSource = dataSource;
   }

   public void createItemPriceTable() {

      try (Connection conn = dataSource.getConnection();
           Statement sm = conn.createStatement()
      ) {
         sm.execute("DROP TABLE IF EXISTS item_prices CASCADE");
         sm.execute("CREATE TABLE item_prices " +
               "(item_id bigint PRIMARY KEY, " +
               "item_price bigint, " +
               "CONSTRAINT fk_item_id_for_itemprice FOREIGN KEY (item_id) REFERENCES items(item_id))"
         );

      } catch (SQLException e) {
         e.printStackTrace();
      }
   }

   public void initSampleItemPrices() {
      // item_id-k 1: banán, 2: narancs, 3: füge, 4: datolya
      List<ItemPrice> itemPrices = new ArrayList<>(List.of(
            new ItemPrice(1, 519),
            new ItemPrice(2, 389),
            new ItemPrice(3, 634),
            new ItemPrice(4, 789)
      ));

      try (Connection conn = dataSource.getConnection()) {
         for (ItemPrice ip : itemPrices) {
            try (PreparedStatement pstm = conn.prepareStatement("INSERT INTO item_prices" +
                  "(item_id, item_price) VALUES (?, ?)")) {
               pstm.setLong(1, ip.getItemId());
               pstm.setLong(2, ip.getPrice());
               pstm.execute();
            }
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }

   }

   public void initSampleItems() {
      List<Item> items = new ArrayList<>(List.of(
            new Item(1, "banán", "származási hely: Kolumbia"),
            new Item(2, "narancs", "származási hely: Spanyolország"),
            new Item(3, "füge", "származási hely: Görögország"),
            new Item(4, "datolya", "származási hely: Egyiptom")
      ));

      try (Connection conn = dataSource.getConnection()) {
         for (Item item : items) {
            try (PreparedStatement pstm = conn.prepareStatement("INSERT INTO items" +
                  "(item_id, name, description) VALUES (?, ?, ?)")) {
               pstm.setLong(1, item.getItemId());
               pstm.setString(2, item.getName());
               pstm.setString(3, item.getDescription());
               pstm.execute();
            }
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }

   }

   public void createItemTable() {
      try (Connection conn = dataSource.getConnection();
           Statement sm = conn.createStatement()
      ) {
         sm.execute("DROP TABLE IF EXISTS items CASCADE");
         sm.execute("CREATE TABLE items " +
               "(item_id bigint PRIMARY KEY, " +
               "name varchar(255), " +
               "description varchar(2048))"
         );

      } catch (SQLException e) {
         e.printStackTrace();
      }

   }

   public void createTransactionTable() {
      try (Connection conn = dataSource.getConnection();
           Statement sm = conn.createStatement()
      ) {
         sm.execute("DROP TABLE IF EXISTS transactions CASCADE");
         sm.execute("CREATE TABLE transactions " +
               "(transaction_id bigint PRIMARY KEY, " +
               "transaction_date varchar(10), " +
               "sum bigint)"
         );

      } catch (SQLException e) {
         e.printStackTrace();
      }

   }

   public void createTransactionAndItemsTable() {
      try (Connection conn = dataSource.getConnection();
           Statement sm = conn.createStatement()
      ) {
         sm.execute("DROP TABLE IF EXISTS trans_and_items CASCADE");
         sm.execute("CREATE TABLE trans_and_items " +
               "(transaction_id bigint, " +
               "item_id bigint, " +
               "quantity bigint, " +
               "CONSTRAINT fk_itemid FOREIGN KEY (item_id) REFERENCES item_prices(item_id), " +
               "CONSTRAINT fk_transid FOREIGN KEY (transaction_id) REFERENCES transactions(transaction_id), " +
               "UNIQUE (transaction_id, item_id))"
         );

      } catch (SQLException e) {
         e.printStackTrace();
      }

   }

}