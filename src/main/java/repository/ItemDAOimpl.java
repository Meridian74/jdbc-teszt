package repository;

import model.Item;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemDAOimpl implements ItemDAO {

   DataSource dataSource;

   public ItemDAOimpl(DataSource dataSource) {
      this.dataSource = dataSource;
   }

   @Override
   public Item findItemById(long id) {
      Item item = new Item();

      try (Connection conn = dataSource.getConnection();
           PreparedStatement pstm = conn.prepareStatement("SELECT * FROM items WHERE item_id = ?")
      ) {
         pstm.setLong(1, id);
         ResultSet rs = pstm.executeQuery();
         if (rs.next()) {
            item.setItemId(rs.getLong("item_id"));
            item.setName(rs.getString("name"));
            item.setDescription(rs.getString("description"));
         } else {
            throw new IllegalArgumentException("The item is not founded by id: " + id);
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }

      return item;
   }

}