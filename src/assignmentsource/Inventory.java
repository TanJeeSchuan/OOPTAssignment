package assignmentsource;


import java.io.*;
import java.util.ArrayList;

public class Inventory {
    private ArrayList<Item> itemList;
    
    public static void main(String[] args) {
        // Create an instance of the Inventory class
        Inventory inventory = new Inventory();

        // Create some sample items and add them to the inventory
        Item item1 = new Item(00001, "BAR00001", 10, 19.99, "Item 1");
        Item item2 = new Item(00002, "BAR00002", 5, 29.99, "Item 2");

        inventory.addItem(item1);
        inventory.addItem(item2);

        // Search for an item by name
        System.out.println("Searching for 'Item 1':");
        inventory.searchItem("Item 1");

        // Delete an item by name
        System.out.println("\nDeleting 'Item 2':");
        inventory.deleteItem("Item 2");

        // Search for the deleted item to confirm it's not in the inventory
        System.out.println("\nSearching for 'Item 2':");
        inventory.searchItem("Item 2");
    }

    public Inventory() {
        itemList = new ArrayList<>();
        loadItemsFromFile(); // Load items from the text file when the Inventory object is created.
    }

    public void addItem(Item item) {
        itemList.add(item);
        saveItemsToFile(); // Save items to the text file when a new item is added.
    }

    public void searchItem(String itemName) {
        for (Item item : itemList) {
            if (item.getItemName().equalsIgnoreCase(itemName)) {
                System.out.println(item);
                return; // Found the item, so exit the loop.
            }
        }
        System.out.println("Item not found.");
    }

    public void deleteItem(String itemName) {
        for (Item item : itemList) {
            if (item.getItemName().equalsIgnoreCase(itemName)) {
                itemList.remove(item);
                saveItemsToFile(); // Save the updated list after removing the item.
                System.out.println("Item deleted.");
                return; // Item found and deleted, so exit the loop.
            }
        }
        System.out.println("Item not found.");
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
