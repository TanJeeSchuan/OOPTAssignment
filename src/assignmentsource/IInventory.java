package assignmentsource;

import java.util.List;

public interface IInventory {
    List<Item> getItemList();
    Item getItem(int itemID);
    void addItem(Item item);
    void removeItem(int itemID);
    void removeItem(Item item);
    boolean isItemExist(int itemID);
    void displayInventory();
    void simpleItemList();
}
