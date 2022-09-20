import config.DBaseServer;
import config.DBaseServerPostgre;
import config.InitDB;
import model.Transaction;
import org.postgresql.ds.PGSimpleDataSource;
import repository.*;
import service.DataBaseInitalizer;
import service.TransactionFileReader;
import service.TransactionFileWriter;
import service.TransactionSaveIntoDB;

import java.util.List;

public class AppController {
    public static void main(String[] args) {

        // create DataSource
        DBaseServer dbServer = new DBaseServerPostgre();
        PGSimpleDataSource dataSource = new PGSimpleDataSource();   // PostgreSQL server database
        dataSource.setURL(dbServer.getURL());
        dataSource.setUser(dbServer.getDbUserName());
        dataSource.setPassword(dbServer.getDbPassword());

        // create repositories
        InitDB initDb = new InitDB(dataSource);
        ItemDAO itemDAO = new ItemDAOimpl(dataSource);
        ItemPriceDAO itemPrice = new ItemPriceDAOimpl(dataSource);
        TransactionDAO transactionDAO = new TransactionDAOimpl(dataSource);

        // db init
        DataBaseInitalizer dataBaseInitalizer = new DataBaseInitalizer(initDb);
        dataBaseInitalizer.createTables();
        dataBaseInitalizer.initSampleData();


        // task: reading a file
        TransactionFileReader tfr = new TransactionFileReader(itemDAO, itemPrice);
        List<Transaction> transactions = tfr.readTransaction("tranzakciok.json");

        // task: writing into file
        TransactionFileWriter tfw = new TransactionFileWriter();
        tfw.saveTransactionsAndCalculatedSums("mentett_tranzakaciok.txt", transactions);

        // task: saving into database
        TransactionSaveIntoDB tsidb = new TransactionSaveIntoDB(transactionDAO);
        tsidb.saveTransactionsIntoDB(transactions);


        // end of program
        System.out.println("Az alkalmazás futása véget ért!");

    }

}