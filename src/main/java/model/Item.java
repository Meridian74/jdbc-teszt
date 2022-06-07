package model;

public class Item {
   private long itemId;
   private String name;
   private String description;

   public Item() {
   }

   public Item(long itemId, String name, String description) {
      this.itemId = itemId;
      this.name = name;
      this.description = description;
   }

   public long getItemId() {
      return itemId;
   }

   public void setItemId(long itemId) {
      this.itemId = itemId;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

}