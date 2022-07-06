package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

   private long transactionId;
   private LocalDate transactionDate;
   private long sum = 0; // it is calculated during the file reading
   private List<Item> items = new ArrayList<>();
   private Map<Long, Long> itemQuantities = new HashMap<>();


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

   public LocalDate getDate() {
      return transactionDate;
   }

}