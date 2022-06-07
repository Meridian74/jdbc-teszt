package repository;

import model.ItemPrice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemPriceDAOpostgre implements PostgreDB, ItemPriceDAO {

   @Override
   public ItemPrice findItemPriceById(long id) {
      ItemPrice itemPrice = new ItemPrice();

      try (Connection conn = PostgreDB.getConnection();
           PreparedStatement pstm = conn.prepareStatement("SELECT * FROM item_prices WHERE item_id = ?")
      ) {
         pstm.setLong(1, id);
         ResultSet rs = pstm.executeQuery();
         if (rs.next()) {
            itemPrice.setItemId(rs.getLong("item_id"));
            itemPrice.setPrice(rs.getLong("item_price"));
         } else {
            throw new IllegalArgumentException("The item price is not founded by id: " + id);
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }

      return itemPrice;
   }

}