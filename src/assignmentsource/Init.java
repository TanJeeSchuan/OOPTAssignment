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
        
        if(!customers.isEmpty()){
            for (String[] customer : customers) {
                if (customer[4].equals("retailer")) {
                    customerList.add(new Retailer(Integer.parseInt(customer[0]), customer[1], customer[2], Integer.parseInt(customer[3]), customer[4]));
                } else {
                    customerList.add(new Wholesaler(Integer.parseInt(customer[0]), customer[1], customer[2], Integer.parseInt(customer[3]), customer[4]));
                }
            }
        }
        return customerList;
    }
    
    public static ArrayList<Customer> initBlacklist() {
        //read file become 2d array
        
        ArrayList<String[]> customers = FileHandler.readFileToArray(FileHandler.BLACKLIST_DB);
        customers.remove(0); // remove the header
        
        ArrayList<Customer> customerList = new ArrayList<>();
        //make them become object
        if(!customers.isEmpty()){
            for (String[] customer : customers) {
                if (customer[4].equals("retailer")) {
                    customerList.add(new Retailer(Integer.parseInt(customer[0]), customer[1], customer[2], Integer.parseInt(customer[3]), customer[4]));
                } else {
                    customerList.add(new Wholesaler(Integer.parseInt(customer[0]), customer[1], customer[2], Integer.parseInt(customer[3]), customer[4]));
                }
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
        if(!items.isEmpty())
        {
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
        }
        return itemList;
    }

    public static ArrayList<Transaction> initTransactions() {
        ArrayList<String[]> transactions = FileHandler.readFileToArray(FileHandler.TRANSACTION_DB);
        transactions.remove(0); // remove the header
        
        ArrayList<Transaction> transactionList = new ArrayList<>();
        
        if(!transactions.isEmpty())
        {
            for (String[] transaction : transactions) {
                if(transaction.length != 0)
                    transactionList.add(new Transaction(transaction));
            }
        }
        return transactionList;
    }
    //init user
    public static ArrayList<SoldItem> initSoldItems(ArrayList<Item> itemList)
    {
        ArrayList<String[]> soldItemsString = FileHandler.readFileToArray(FileHandler.SOLD_ITEM_DB);
        soldItemsString.remove(0); // remove the header
        
        ArrayList<SoldItem> soldItemList = new ArrayList<>();
        
        if(!soldItemsString.isEmpty())
        {
            for (String[] soldItem: soldItemsString){
                for (Item i: itemList)
                {
                    if(i.getItemID() == Integer.parseInt(soldItem[1]))
                        soldItemList.add(new SoldItem(Integer.parseInt(soldItem[0]), i, Integer.parseInt(soldItem[2])));
                }
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
        
        if(!usersString.isEmpty())
        {
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
        }
        return user;
    }
}
