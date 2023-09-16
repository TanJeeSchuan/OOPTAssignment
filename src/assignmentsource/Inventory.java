package assignmentsource;

import java.util.List;

public class Inventory implements IInventory{

    private List<Item> itemList;

    public Inventory(List<Item> itemList) {
        this.itemList = itemList;
    }

    @Override
    public List<Item> getItemList() {
        return itemList;
    }

    @Override
    public Item getItem(int itemID) {
        for (Item item : itemList) {
            if (item.getItemID() == itemID) {
                return item;
            }
        }
        return null;
    }

    @Override
    public void addItem(Item item) {
        itemList.add(item);
        FileHandler.writeFile(FileHandler.INVENTORY_DB, item.toString());
    }

    @Override
    public void removeItem(Item item) {
        itemList.remove(item);
    }

    @Override
    public void removeItem(int itemID) {
        if (isItemExist(itemID)) {
            removeItem(getItem(itemID));
            FileHandler.removeRowByID(FileHandler.INVENTORY_DB, String.valueOf(itemID));
        } else {
            System.out.println("\nItem with id " + itemID + " not found\n");
        }
    }

    @Override
    public boolean isItemExist(int itemID){
        for (Item item : itemList) {
            if (item.getItemID() == itemID) {
                return true;
            }
        }
        return false;
    }

    @Override
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

    @Override
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
