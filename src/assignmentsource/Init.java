/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignmentsource;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tan Jee Schuan/ 
 */
public class Init {

    //return ArrayList of customers
    public static List<Customer> initCustomers() {
        //read file become 2d array
        
        ArrayList<String[]> customers = FileHandler.readFileToArray(FileHandler.CUSTOMER_DB);
        customers.remove(0); // remove the header
        
        //prepare a list
        ArrayList<Customer> customerList = new ArrayList<>();
        //make them become object
        for (String[] customer : customers) {
            if (customer[4].equals("retailer")) {
                customerList.add(new Retailer(Integer.parseInt(customer[0]), customer[1], customer[2], Integer.parseInt(customer[3]), customer[4]));
            } else {
                customerList.add(new Wholesaler(Integer.parseInt(customer[0]), customer[1], customer[2], Integer.parseInt(customer[3]), customer[4]));
            }
        }
        return customerList;
    }
    //init user

    //init item
    public static ArrayList<Item> initItems() {
        ArrayList<String[]> items = FileHandler.readFileToArray(FileHandler.INVENTORY_DB);
        items.remove(0); // remove the header
        ArrayList<Item> itemList = new ArrayList<>();
        //
        for (String[] item : items) {
            itemList.add(new Item(Integer.parseInt(item[0]), item[1], item[2], Integer.parseInt(item[3]), Double.parseDouble(item[4]), Double.parseDouble(item[5])));
        }
        return itemList;
    }

    public static List<Transaction> initTransactions() {
        ArrayList<String[]> transactions = FileHandler.readFileToArray(FileHandler.TRANSACTION_DB);
        transactions.remove(0); // remove the header
        ArrayList<Transaction> transactionList = new ArrayList<>();
        for (String[] transaction : transactions) {
            transactionList.add(new Transaction(Integer.parseInt(transaction[0]), Integer.parseInt(transaction[1]), Double.parseDouble(transaction[3]), Integer.parseInt(transaction[2]), Integer.parseInt(transaction[4]), Double.parseDouble(transaction[5])));
        }
        return transactionList;
    }
    //init user

    public static String intListToString(List<Integer> soldItemsID) {
        StringBuilder str = new StringBuilder();
        if (soldItemsID.size() > 0) {
            for (int i = 0; i < soldItemsID.size() - 1; i++) {
                str.append(soldItemsID.get(i)).append(":");
            }
            str.append(soldItemsID.get(soldItemsID.size() - 1));
        }
        return str.toString();
    }
    //initcustomer

    public static ArrayList<Sales> initSales() {
        ArrayList<String[]> sales = FileHandler.readFileToArray(FileHandler.SALES_DB);
        sales.remove(0); // remove the header
        ArrayList<Sales> salesList = new ArrayList<>();
        for (String[] sale : sales) {
            salesList.add(new Sales(Integer.parseInt(sale[0]), sale[1], Integer.parseInt(sale[2]), Tools.stringToIntList(sale[3]), Tools.stringToIntList(sale[4])));
        }
        return salesList;
    }
    public Sales a;
}
