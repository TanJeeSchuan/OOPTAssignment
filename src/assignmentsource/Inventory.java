/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignmentsource;

/**
 *
 * @author Yeoh Ming Zhe
 */
import java.io.BufferedReader;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Inventory {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("====================");
            System.out.println("Welcome to Inventory");
            System.out.println("====================\n");
            System.out.println("Choose an option:");
            System.out.println("1. Add Item");
            System.out.println("2. Search Item");
            System.out.println("3. Exit");

            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1 ->
                    addItem();
                case 2 ->
                    searchItem();
                case 3 -> {
                    System.out.println("Exiting the program.");
                    return;
                }
                default ->
                    System.out.println("Invalid choice. Please choose again.");
            }
        }

    }

    //Add Item function
    public static void addItem() {

        Scanner scanner = new Scanner(System.in);

        String barcode = generateBarcode(Item.itemID);

        System.out.print("Enter item name: ");
        String itemName = scanner.nextLine();

        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();

        System.out.print("Enter price (per item): RM");
        double price = scanner.nextDouble();

        // Create an Item object
        Item newItem = new Item(Item.itemID, barcode, quantity, price, itemName);

        // Save item to file
        saveItemToFile(newItem);

        Item.itemID++;

        System.out.println("Item added successfully.");
    }

    // Save item to file
    public static void saveItemToFile(Item item) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Inventory.txt", true))) {
            writer.write(item.toString());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //main search Item
    public static void searchItem() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter item name to search: ");
        String searchName = scanner.nextLine();

        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader("Inventory.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");  //used as separator, the string is split between ","
                String itemName = parts[0];

                // If the item name matches the search name (case-insensitive)
                if (itemName.equalsIgnoreCase(searchName)) {
                    System.out.printf("\n");
                    System.out.printf("\n");
                    System.out.println("Item found:");
                    System.out.println(line);  // Print the entire line as the item information
                    found = true;   //Indicate that the item was found
                    break;  // Exit the loop since the item was found
                }
            }

            if (!found) {
                System.out.println("Item not found.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String generateBarcode(int itemID) {
        String formattedItemID = String.format("%05d", itemID); // Format itemID as a 5-digit number
        return "BAR" + formattedItemID; // Combine with "BAR" prefix
    }

}
