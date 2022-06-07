package repository;

import model.Item;
import model.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class TransactionDAOpostgre implements PostgreDB, TransactionDAO {

   @Override
   public void saveAll(List<Transaction> transactions) {

      try (Connection conn = PostgreDB.getConnection()) {
         for (Transaction tr : transactions) {
            try (PreparedStatement pstm = conn.prepareStatement("INSERT INTO transactions" +
                  "(transaction_id, transaction_date, sum) VALUES (?, ?, ?)")) {
               pstm.setLong(1, tr.getTransactionId());
               pstm.setString(2, tr.getDate().toString()); // egyszerűsített megoldás
               pstm.setLong(3, tr.getSum());
               pstm.execute();
            }

            List<Item> items = tr.getItems();
            for(Item item : items) {
               try (PreparedStatement pstm = conn.prepareStatement("INSERT INTO trans_and_items" +
                     "(transaction_id, item_id, quantity) VALUES (?, ?, ?)")) {
                  pstm.setLong(1, tr.getTransactionId());
                  pstm.setLong(2, item.getItemId());
                  long itemId = item.getItemId();
                  Long quantity = tr.getItemQuantities().get(itemId);
                  pstm.setLong(3, quantity);
                  pstm.execute();
               }
            }
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }

}