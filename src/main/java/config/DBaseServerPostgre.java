package config;

public class DBaseServerPostgre implements DBaseServer {

   private static final String JDBC_CONNECTOR = "jdbc:postgresql://";
   private static final String SERVER_HOST_NAME = "localhost";
   private static final String PORT_NUMBER = "5436";
   private static final String DATABASE_NAME = "jdbc_teszt";
   private static final String DB_USER_NAME = "user";
   private static final String DB_PASSWORD = "abc123";

   @Override
   public String getURL() {
      return JDBC_CONNECTOR + SERVER_HOST_NAME + ":" + PORT_NUMBER + "/" + DATABASE_NAME;
   }

   @Override
   public String getDbUserName() {
      return DB_USER_NAME;
   }

   @Override
   public String getDbPassword() {
      return DB_PASSWORD;
   }

}