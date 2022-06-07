package model;

public class ItemPrice {

   long itemId;
   long price;

   public ItemPrice() {
   }

   public ItemPrice(long itemId, long price) {
      this.itemId = itemId;
      this.price = price;
   }

   public long getItemId() {
      return itemId;
   }

   public void setItemId(long itemId) {
      this.itemId = itemId;
   }

   public long getPrice() {
      return price;
   }

   public void setPrice(Long price) {
      this.price = price;
   }

}