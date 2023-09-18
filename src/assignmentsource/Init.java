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
    public static ArrayList<Customer> initCustomers() {
        //read file become 2d array
        
        ArrayList<String[]> customers = FileHandler.readFileToArray(FileHandler.CUSTOMER_DB);
        customers.remove(0); // remove the header
        
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

    //init item (Parse string arrays into Item objects)
    public static ArrayList<Item> initItems() {
        ArrayList<String[]> items = FileHandler.readFileToArray(FileHandler.INVENTORY_DB);
        items.remove(CSVFile.HEADER_INDEX); // remove the header
        
        ArrayList<Item> itemList = new ArrayList<>();
        int i = 1;
        for (String[] item : items) {
            try
            {
                itemList.add(new Item(item));
                i++;
            }
            catch(Exception e)
            {
                System.out.printf("Row %d is invalid\n\n", i);
            }   
        }
        return itemList;
    }

    public static ArrayList<Transaction> initTransactions() {
        ArrayList<String[]> transactions = FileHandler.readFileToArray(FileHandler.TRANSACTION_DB);
        transactions.remove(0); // remove the header
        
        ArrayList<Transaction> transactionList = new ArrayList<>();
        
        for (String[] transaction : transactions) {
            if(transaction.length != 0)
                transactionList.add(new Transaction(transaction));
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

//    public static ArrayList<Sales> initSales() {
//        ArrayList<String[]> sales = FileHandler.readFileToArray(FileHandler.SALES_DB);
//        sales.remove(0); // remove the header
//        ArrayList<Sales> salesList = new ArrayList<>();
//        for (String[] sale : sales) {
//            salesList.add(new Sales(Integer.parseInt(sale[0]), sale[1], Integer.parseInt(sale[2]), Tools.stringToIntList(sale[3]), Tools.stringToIntList(sale[4])));
//        }
//        return salesList;
//    }
//    public Sales a;
    
    public static ArrayList<SoldItem> initSoldItems(ArrayList<Item> itemList)
    {
        ArrayList<String[]> soldItemsString = FileHandler.readFileToArray(FileHandler.SOLD_ITEM_DB);
        soldItemsString.remove(0); // remove the header
        
        ArrayList<SoldItem> soldItemList = new ArrayList<>();
        
        for (String[] soldItem: soldItemsString){
            for (Item i: itemList)
            {
                if(i.getItemID() == Integer.parseInt(soldItem[1]))
                    soldItemList.add(new SoldItem(Integer.parseInt(soldItem[0]), i, Integer.parseInt(soldItem[2])));
            }
        }
        
        return soldItemList;
    }
    
    public static ArrayList<Sales> initSales(ArrayList<Customer> cust, ArrayList<Transaction> transaction, ArrayList<SoldItem> soldItems){
        ArrayList<String[]> sales = FileHandler.readFileToArray(FileHandler.SALES_DB);
        sales.remove(0); // remove the header
        ArrayList<Sales> salesList = new ArrayList<>();
        for (String[] sale : sales) {
            Sales s = new Sales(sale, cust, transaction, soldItems);
            salesList.add(s);
        }
        
        return salesList;
    }
    
    public static ArrayList<User> initUsers(){
        ArrayList<String[]> usersString = FileHandler.readFileToArray(FileHandler.USER_DB);
        usersString.remove(0);
        
        ArrayList<User> user = new ArrayList<>();
        for (String[] userString: usersString)
        {
            if(null != userString[0])
                switch (userString[0]) {
                case "Cashier":
                    user.add(new Cashier(userString));
                    break;
                case "Management":
                    user.add(new Management(userString));
                    break;
                case "Admin":
                    user.add(new Admin(userString));
                    break;
                default:
                    break;
            }
        }
        
        return user;
    }
}
