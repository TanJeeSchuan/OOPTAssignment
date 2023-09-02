package assignmentsource;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Inventory {

    private ArrayList<Item> itemList;

    public static void main(String[] args) {
        // Create an instance of the Inventory class
        Inventory inventory = new Inventory();

        // Create some sample items and add them to the inventory
        Item item1 = new Item(00001, "BAR00001", 10, 19.99, "Item 1");
        Item item2 = new Item(00002, "BAR00002", 5, 29.99, "Item 2");
        Item item3 = new Item(00003, "BAR00003", 8, 83.33, "Item 3");
        Item item4 = new Item(00004, "BAR00004", 32, 59.99, "Item 4");
        Item item5 = new Item(00005, "BAR00005", 20, 60.00, "Item 5");

        inventory.addItem(item1);
        inventory.addItem(item2);
        inventory.addItem(item3);
        inventory.addItem(item4);
        inventory.addItem(item5);

        // Search for an item by name
        System.out.println("Searching for 'Item 1':");
        System.out.println(inventory.searchItem("Item 1").toString());

        // Delete an item by name
        System.out.println("\nDeleting 'Item 2':");
        inventory.deleteItem("Item 2");

        // Search for the deleted item to confirm it's not in the inventory
        System.out.println("\nSearching for 'Item 2':");
        inventory.searchItem("Item 2");

        System.out.println("\nModify for 'item 3':");
        inventory.modifyItemToFile(item3, item5);
        System.out.println(inventory.seachItem(item5));

        System.out.println("\n\n");
        inventory.displayAllItems();

    }

    public Inventory() {
        itemList = new ArrayList<>();
        loadItemsFromFile(); // Load items from the text file when the Inventory object is created.
    }

    public ArrayList getItem() {
        return itemList;
    }

    public void addItem(Item item) {
        itemList.add(item);
        saveItemsToFile(); // Save items to the text file when a new item is added.
    }

    public Item seachItem(Item newItem) {
        for (Item item : itemList) {
            if (item.equals(newItem)) {
                return item;
            }
        }
        return null;
    }

    public Item searchItem(String itemName) {
        for (Item item : itemList) {
            if (item.getItemName().equalsIgnoreCase(itemName)) {
                return item; // Found the item, so exit the loop.
            }
        }
        return null;
    }

    public void deleteItem(String itemName) {
        boolean itemsRemoved = false;
        Iterator<Item> iterator = itemList.iterator();

        while (iterator.hasNext()) {
            Item item = iterator.next();
            if (item.getItemName().equalsIgnoreCase(itemName)) {
                iterator.remove();
                itemsRemoved = true;
            }
        }

        if (itemsRemoved) {
            saveItemsToFile(); // Save the updated list after removing items.
            System.out.println("Items with the name '" + itemName + "' deleted.");
        } else {
            System.out.println("No items with the name '" + itemName + "' found.");
        }
    }

    public void modifyItemToFile(Item targetItem, Item newItem) {
        boolean itemModified = false;

        for (int i = 0; i < itemList.size(); i++) {
            Item currentItem = itemList.get(i);
            if (currentItem.getItemName().equalsIgnoreCase(targetItem.getItemName())) {
                // Replace the current item with the new item's details
                currentItem.setItemID(newItem.getItemID());
                currentItem.setBarcode(newItem.getBarcode());
                currentItem.setQuantity(newItem.getQuantity());
                currentItem.setPrice(newItem.getPrice());

                itemModified = true;
                break; // Exit the loop once the item is found and modified.
            }
        }

        if (itemModified) {
            saveItemsToFile(); // Save the updated list after modifying the item.
            System.out.println("Item '" + targetItem.getItemName() + "' modified.");
        } else {
            System.out.println("Item '" + targetItem.getItemName() + "' not found.");
        }
    }

    public void modifyItemToFile(String itemName, Item newItem) {

        boolean itemModified = false;

        for (int i = 0; i < itemList.size(); i++) {
            Item item = itemList.get(i);
            if (item.getItemName().equalsIgnoreCase(itemName)) {
                //Replace the item with the new item
                itemList.set(i, newItem);
                itemModified = true;
                break;
            }
        }

        if (itemModified) {
            saveItemsToFile();
            System.out.println("The Item: " + itemName + " has been modified.");
        } else {
            System.out.println("Item with the name: " + itemName + " not found!");
        }
    }

    public void displayAllItems() {
        try (BufferedReader reader = new BufferedReader(new FileReader("Inventory.txt"))) {
            String line;
            System.out.println("Inventory Items:");
            while ((line = reader.readLine()) != null) {
                // Print each line from the text file
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading items from file: " + e.getMessage());
        }
    }

    private void loadItemsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("Inventory.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Parse each line from the text file and create Item objects
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    int itemID = Integer.parseInt(parts[1].trim());
                    String barcode = parts[4].trim();
                    String itemName = parts[0].trim();
                    int quantity = Integer.parseInt(parts[2].trim());

                    // Remove the currency symbol "RM " from the price string before parsing it
                    double price = Double.parseDouble(parts[3].trim().replace("RM ", ""));

                    Item item = new Item(itemID, barcode, quantity, price, itemName);
                    itemList.add(item);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading items from file: " + e.getMessage());
        }
    }

    private void saveItemsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Inventory.txt"))) {
            for (Item item : itemList) {
                writer.write(item.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving items to file: " + e.getMessage());
        }
    }
}
