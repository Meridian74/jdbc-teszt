package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface PostgreDB {

   String JDBC_CONNECTOR = "jdbc:postgresql://";
   String SERVER_HOSTNAME = "localhost";
   String PORT_NUMBER = "5436";
   String DB_NAME = "jdbc_teszt";
   String DB_USERNAME = "user";
   String DB_PASSWORD = "abc123";

   static Connection getConnection() {
      Connection connection = null;
      try {
         connection = DriverManager.getConnection(JDBC_CONNECTOR +
                     SERVER_HOSTNAME + ":" + PORT_NUMBER + "/" + DB_NAME,
               DB_USERNAME, DB_PASSWORD);

      } catch (SQLException e) {
         e.printStackTrace();
      }

      return connection;
   }

}