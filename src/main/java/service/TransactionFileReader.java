package service;

import model.Item;
import model.ItemPrice;
import model.Transaction;
import repository.ItemDAO;
import repository.ItemPriceDAO;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TransactionFileReader {

   private final ItemDAO itemDAO;
   private final ItemPriceDAO itemPriceDAO;

   public TransactionFileReader(ItemDAO itemDAO, ItemPriceDAO itemPriceDAO) {
      this.itemDAO = itemDAO;
      this.itemPriceDAO = itemPriceDAO;
   }

   public List<Transaction> readTransaction(String fileName) {

      try (BufferedReader reader = Files.newBufferedReader(Path.of(fileName))) {
         List<Transaction> transactions = new ArrayList<>();

         String line;
         Transaction transaction = new Transaction();
         Item item = new Item();

         while ((line = reader.readLine()) != null) {
            if (line.contains("transactionId")) {
               transaction = new Transaction();
               long id = getNumFromLine(line);
               transaction.setTransactionId(id);
               transactions.add(transaction);
            }
            else if (line.contains("itemId")) {
               item = getItem(line);
               ItemPrice itemPrice = getItemPrice(item.getItemId());
               transaction.addItem(item);
               transaction.setSum(transaction.getSum() + itemPrice.getPrice());

            }
            else if (line.contains("purchaseTime")) {
               String dateString = getDateString(line);
               LocalDate date = LocalDate.parse(dateString);
               transaction.setTransactionDate(date);
            }
            else if (line.contains("quantity")) {
               Long quantity = getNumFromLine(line);
               transaction.addQuantity(item.getItemId(), quantity);
            }

         }

         return transactions;
      }
      catch(IOException ioe) {
         throw new IllegalStateException("Cannot reach the file!", ioe);
      }
   }

   private Long getNumFromLine(String line) {
      String[] splits = line.split(":");
      String[] nums = splits[1].split(",");
      return Long.parseLong(nums[0].trim());
   }

   private Item getItem(String line) {
      String[] splits = line.split(":");
      String[] nums = splits[1].split(",");
      long id = Long.parseLong(nums[0].trim());
      return itemDAO.findItemById(id);
   }

   private ItemPrice getItemPrice(long itemId) {
      return itemPriceDAO.findItemPriceById(itemId);
   }

   private String getDateString(String line) {
      String[] splits = line.split(":");
      int index = splits[1].indexOf("\"");
      return splits[1].substring(index + 1, index + 11);
   }

}