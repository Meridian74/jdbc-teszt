package service;

import model.Transaction;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class TransactionFileWriter {

   public void saveTransactionsAndCalculatedSums(String fileName, List<Transaction> transactions) {
      try (BufferedWriter br = Files.newBufferedWriter(Path.of(fileName))) {
         for (Transaction tr : transactions) {
            String newLine = "tranzakció ID: " + tr.getTransactionId() + ", ";
            newLine += "végösszege: " + tr.getSum() + "\n";
            br.write(newLine);
         }

      } catch (IOException ioe) {
         throw new IllegalStateException("Can't write file", ioe);
      }
   }

}