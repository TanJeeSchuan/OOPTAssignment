package assignmentsource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SalesReport extends Report{

    // analyze each item sold, and quantity sold, and total sales
    // analyze the wholesaler // retailer visited, and total spend
    private List<Sales> salesList;
    //array
    private List<SoldItem> soldItemList = new ArrayList<>();
    //hash map
    private HashMap<String, Double> wholesalerSpending = new HashMap<>();
    private HashMap<String, Double> retailerSpending = new HashMap<>();
    private int totalPoints;
    private double totalSales;

    public SalesReport() {
        super();
        initialize();
    }
    // Method to initialize the SalesReport
    @Override
    public void initialize() {
        this.salesList = StaticContainer.salesList;
    }
    
    // Method to print the sales report
    @Override
    public void print() {
        // Call the template method to print the report header
        template();
        System.out.printf("%-20s%-18s%-10s%-12s\n", "Datetime", "Item Name", "Quantity", "Subtotal");
        System.out.println("----------------------------------------------------------");
         // Loop through each sales record in the sales list
        for (Sales sales : salesList) {
            for (SoldItem soldItem : sales.getSoldItems()) {
                System.out.printf("%-20s%-18s%-10d%-12.2f\n",
                        sales.getTimeOfSale(),
                        soldItem.getSoldItem().getItemName(),
                        soldItem.getQuantity(),
                        soldItem.getSubTotalByRole(sales.getCustomer().getRole())
                );
                // Calculate the total sales by adding the subtotal
                // subtotal is calculated based on the customer's role
                totalSales += soldItem.getSubTotalByRole(sales.getCustomer().getRole());
            }
        }
         // Print total sales and total points
        System.out.println("----------------------------------------------------------");
        System.out.printf("%-16s%-18s%-10s%-12.2f\n", "", "", "Total:", totalSales);
        System.out.println("----------------------------------------------------------");
        System.out.println("Total Points Given: " + totalPoints);
        System.out.println("----------------------------------------------------------");
        // Print spending by wholesalers
        System.out.println("Wholesaler Spending:");
        System.out.printf("%-20s%-10s%18s\n", "Name", "Total", "Points Collected");
        System.out.println("----------------------------------------------------------");
        if (wholesalerSpending.isEmpty()) {
            System.out.println("No wholesaler visited.");
        } else {
              // Loop through each entry in the wholesalerSpending HashMap
            for (String name : wholesalerSpending.keySet()) {
                // Print the name, total spending, and points collected for each wholesaler
                System.out.printf("%-20s%-10.2f%18d\n",
                        name, wholesalerSpending.get(name),
                        (int) (wholesalerSpending.get(name) / 10)
                );
            }
        }
        System.out.println("----------------------------------------------------------");
        System.out.println("Retailer Spending:");
        System.out.printf("%-20s%-10s%18s\n", "Name", "Total", "Points Collected");
        System.out.println("----------------------------------------------------------");
        if (retailerSpending.isEmpty()) {
            System.out.println("No retailer visited.");
        } else {
            for (String name : retailerSpending.keySet()) {
                System.out.printf("%-20s%-10.2f%18d\n",
                        name, retailerSpending.get(name),
                        (int) (retailerSpending.get(name) / 10)
                );
            }
        }
        System.out.println("----------------------------------------------------------");
    }

    @Override
    public void template() {
        System.out.println("\n----------------------------------------------------------");
        System.out.println("\t\t\t\tSales Report");
        System.out.println("----------------------------------------------------------");
    }

    @Override
    public void analyze() {
        for (Sales sales : salesList) {
            this.soldItemList.addAll(sales.getSoldItems());
            this.totalPoints += sales.getPoints(sales.getSubTotalByRole());
             // Check if the customer's role in the current sale is "wholesaler"
            if (sales.getCustomer().getRole().equals("wholesaler")) {
                // Check if wholesalerSpending HashMap already contains the customer's name
                if (wholesalerSpending.containsKey(sales.getCustomer().getName())) {
                     // Update the existing spending value by adding the subtotal of the current sale
                    wholesalerSpending.put(
                            sales.getCustomer().getName(),
                            wholesalerSpending.get(sales.getCustomer().getName()) + sales.getSubTotalByRole()
                    );
                } else {
                    wholesalerSpending.put(sales.getCustomer().getName(), sales.getSubTotalByRole());
                }
            } else {
                if (retailerSpending.containsKey(sales.getCustomer().getName())) 
                {
                    retailerSpending.put(
                            sales.getCustomer().getName(),
                            retailerSpending.get(sales.getCustomer().getName()) + sales.getSubTotalByRole()
                    );
                } else {
                    retailerSpending.put(sales.getCustomer().getName(), sales.getSubTotalByRole());
                }
            }
        }
    }

}
