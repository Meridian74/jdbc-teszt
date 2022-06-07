package model;

import java.time.LocalDate;
import java.util.*;


public class Transaction {
   private long transactionId;
   private LocalDate transactionDate;
   private long sum = 0; // it is calculated during the file reading

   private List<Item> items = new ArrayList<>();
   private Map<Long, Long> itemQuantities = new HashMap<>();

   public Transaction() {
   }

   public Transaction(long transactionId, LocalDate transactionDate, long sum, List<Item> items, Map<Long, Long> itemQuantities) {
      this.transactionId = transactionId;
      this.transactionDate = transactionDate;
      this.sum = sum;
      this.items = items;
      this.itemQuantities = itemQuantities;
   }

   public LocalDate getTransactionDate() {
      return this.transactionDate;
   }

   public void setTransactionDate(LocalDate transactionDate) {
      this.transactionDate = transactionDate;
   }

   public Map<Long, Long> getItemQuantities() {
      return itemQuantities;
   }

   public void setItemQuantities(Map<Long, Long> itemQuantities) {
      this.itemQuantities = itemQuantities;
   }

   public long getTransactionId() {
      return transactionId;
   }

   public void setTransactionId(long transactionId) {
      this.transactionId = transactionId;
   }

   public LocalDate getDate() {
      return transactionDate;
   }

   public void setDate(LocalDate date) {
      this.transactionDate = date;
   }

   public List<Item> getItems() {
      return items;
   }

   public void setItems(List<Item> items) {
      this.items = items;
   }

   public long getSum() {
      return sum;
   }

   public void setSum(long sum) {
      this.sum = sum;
   }

   public void addItem(Item item) {
      if (item != null) {
         items.add(item);
      }
   }

   public void addQuantity(Long itemId, Long quantity) {
      if (itemId != null && quantity != null) {
         itemQuantities.put(itemId, quantity);
      }
   }

}