package assignmentsource;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Inventory{
    
    public static final String[] FILE_HEADER = {"itemID,barCode,itemName,quantity,price,bulkPrice"};

    private ArrayList<Item> itemList;

    public Inventory(ArrayList<Item> itemList) {
        this.itemList = itemList;
    }
    
    public Inventory() {
        itemList = new ArrayList<Item>();
    }

    public ArrayList<Item> getItemList() {
        return itemList;
    }

    public Item getItem(int itemID) {
        for (Item item : itemList) {
            if (item.getItemID() == itemID)
                return item;
        }
        return null;
    }
    public Item getItem(String itemID) {
        for (Item item : itemList) {
            if (item.getItemID() == Integer.parseInt(itemID))
                return item;
        }
        return null;
    }
    
    public void addItem(Item item) {
        itemList.add(item);
        FileHandler.writeFile(FileHandler.INVENTORY_DB, item.toCSV());
    }

    public void removeItem(Item item) {
        if(item == null)
            return;
        
        if(!itemList.remove(item))
            System.out.println("Item to delete not found!");
        else
            CSVFile.rewriteFileObj(FileHandler.INVENTORY_DB, itemList, FILE_HEADER);
    }
    
    public void removeItem(int itemID) {
        if (isItemExist(itemID)) {
            removeItem(getItem(itemID));
            CSVFile.removeRowByID(FileHandler.INVENTORY_DB, String.valueOf(itemID));
        } else {
            System.out.println("\nItem with id " + itemID + " not found\n");
        }
    }
    public void removeItem(String itemID) {
        if (isItemExist(itemID)) {
            removeItem(getItem(itemID));
            CSVFile.removeRowByID(FileHandler.INVENTORY_DB, itemID);
        } else {
            System.out.println("\nItem with id " + itemID + " not found\n");
        }
    }
   
    public boolean isItemExist(int itemID){
        for (Item item : itemList) {
            if (item.getItemID() == itemID) {
                return true;
            }
        }
        return false;
    }
    public boolean isItemExist(String itemID){
        for (Item item : itemList) {
            if (item.getItemID() == Integer.parseInt(itemID))
                return true;
        }
        return false;
    }
    
    public void updateItemQty(SoldItem i){
        i.getSoldItem().updateQuantity(i.getQuantity());
    }
    
    public void updateItemQty(ArrayList<SoldItem> soldItemList){
        for (SoldItem i: soldItemList){
            updateItemQty(i);
        }
    }
    //custom
    public void updateFile(){
        CSVFile.rewriteFileObj(FileHandler.INVENTORY_DB, itemList, FILE_HEADER);
    }

    public void displayInventory() {
        System.out.printf("\n%-10s%-15s%-20s%-10s%-10s%-10s\n", "Item ID", "Barcode", "Item Name", "Quantity", "Price", "Bulk Price");
        System.out.println("---------------------------------------------------------------------------");
        for (Item item : itemList) {
            System.out.printf("%-10d%-15s%-20s%-10d%-10.2f%10.2f\n",
                    item.getItemID(), item.getBarCode(), item.getItemName(),
                    item.getQuantity(), item.getPrice(), item.getBulkPrice()
            );
        }
        System.out.println("---------------------------------------------------------------------------\n");
    }
    
    public void displayInventory(ArrayList<Item> itemList) {
        System.out.printf("\n%-5s%-10s%-15s%-30s%-10s%-10s%-10s\n","Num", "Item ID", "Barcode", "Item Name", "Quantity", "Price", "Bulk Price");
        System.out.println("---------------------------------------------------------------------------");
        int i = 1;
        for (Item item : itemList) {
            System.out.printf("%-5d%-10d%-15s%-30s%-10d%-10.2f%10.2f\n",
                    i,
                    item.getItemID(), item.getBarCode(), item.getItemName(),
                    item.getQuantity(), item.getPrice(), item.getBulkPrice()
            );
            i++;
        }
        System.out.println("---------------------------------------------------------------------------\n");
    }

    public void simpleItemList(){
        System.out.printf("\n%-10s%-20s%-10s%-10s\n", "Item ID", "Item Name", "Price", "Bulk Price");
        System.out.println("-----------------------------------------------------");
        for (Item item : itemList) {
            System.out.printf("%-10d%-20s%-10.2f%-10.2f\n",
                    item.getItemID(), item.getItemName(),
                    item.getPrice(), item.getBulkPrice()
            );
        }
        System.out.println("-----------------------------------------------------\n");
    }
}
