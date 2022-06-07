import model.Transaction;
import repository.InitDB;
import service.TransactionFileReader;
import service.TransactionFileWriter;
import service.TransactionSaveIntoDB;

import java.util.List;

public class AppController {
    public static void main(String[] args) {
        // db init
        InitDB ipi = new InitDB();
        // create tables
        ipi.createItemTable();
        ipi.createItemPriceTable();
        ipi.createTransactionTable();
        ipi.createTransactionAndItemsTable();

        // fill sample data
        ipi.initSampleItems();
        ipi.initSampleItemPrices();

        // task: reading a file
        TransactionFileReader tfr = new TransactionFileReader();
        List<Transaction> transactions = tfr.readTransaction("tranzakciok.json");

        // task: writing into file
        TransactionFileWriter tfw = new TransactionFileWriter();
        tfw.saveTransactionsAndCalculatedSums("mentett_tranzakaciok.txt", transactions);

        // task: saving into database
        TransactionSaveIntoDB tsidb = new TransactionSaveIntoDB();
        tsidb.saveTransactionsIntoDB(transactions);

        // end of program
        System.out.println("Az alkalmazás futása véget ért!");
    }

}