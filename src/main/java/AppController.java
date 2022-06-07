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

        // tasks: olvasás
        TransactionFileReader tfr = new TransactionFileReader();
        List<Transaction> transactions = tfr.readTransaction("tranzakciok.json");

        // tasks: file-ba írás
        TransactionFileWriter tfw = new TransactionFileWriter();
        tfw.saveTransactionsAndCalculatedSums("mentett_tranzakaciok.txt", transactions);

        // tasks: adatbázisba mentés
        TransactionSaveIntoDB tsidb = new TransactionSaveIntoDB();
        tsidb.saveTransactionsIntoDB(transactions);

        // vége a program futásának
        System.out.println("Az alkalmazás futása véget ért!");
    }

}