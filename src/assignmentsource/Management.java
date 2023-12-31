/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignmentsource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author Tan Jee Schuan
 */
public class Management extends User{
    
    public Management(String[] data){
        super(data);
    }
    
    public Management (String username, String password, String name, String phoneNumber){
        super(username, password, name, phoneNumber);
    }
    
    @Override
    public boolean mainMenu() {
        Scanner sc = new Scanner(System.in);
                
        int menuSel = 0;
        do{
            String[] s = {"Management", "Manage Transactions", "Manage Customers", "Manage Inventory", "Sales Analysis", "Item Sale Analysis", "Logout"};
            menuSel = Menu.menu(s);
            switch(menuSel)
            {
                case 1:
                    transactionManagment();
                    break;

                case 2:
                    customerManagement();
                    break;

                case 3:
                    inventoryManagement();
                    break;
                    
                case 4:
                    customerSpendingAnalysis();
                    break;
                    
                case 5:
                    itemSaleAnalysis();
                    break;
                    
                case 6:
                    return true;
            }  
        }while(menuSel != 6);
        
        return false;
    }
    
    public void customerManagement(){
        Scanner sc = new Scanner(System.in);
        
        StationaryPOS sPOS = StationaryPOS.getInstance();
        
        int sel = 0;
        do{
            String[] s = {"Customer Management", "Add Customer", "Modify Customer", "Blacklist Customer", "Display All Customers", "Exit"};
            sel = Menu.menu(s);
            
            switch(sel)
            {
                case 1:
                    sPOS.addCustomer();
                    break;

                case 2:
                    Customer c = (Customer)Tools.objectSelection(sPOS.cust);
                    if(c != null)
                        sPOS.modfiyCustomer(c);
                    else
                        System.out.println("No Customer Selected!");
                    break;

                case 3:
                    blacklistManagement();
                    break;
                    
                case 4:
                    System.out.println(Customer.FORMAT_HEADER);
                    for(Customer cust: sPOS.cust){
                        System.out.println(cust.toFormattedString());
                    }
                    break;
                    
                case 5:
                    break;
            }  
        }while(sel != 5);
    }
    
    public void blacklistManagement(){
        Scanner sc = new Scanner(System.in);
        ArrayList<Customer> blacklistCustomers = Init.initBlacklist();
        
        StationaryPOS sPOS = StationaryPOS.getInstance();
        
        int sel = 0;
        do{
            String[] s = {"Blacklist Management", "Add Customer to blacklist", "Remove Customer from blacklist", "Display all blacklisted customer", "Exit"};
            sel = Menu.menu(s);

            switch(sel){

                case 1:
                    Customer cust = (Customer)Tools.objectSelection(sPOS.cust);

                    if(cust != null){
                        sPOS.removeCustomer(cust);            //remove from file

                        blacklistCustomers.add(cust);
                        CSVFile.writeFile(FileHandler.BLACKLIST_DB, cust.toCSV());
                    }
                    else
                        System.out.println("No Customer Selected");
                    break;
                   
                case 2:
                    Customer selC = (Customer)Tools.objectSelection(blacklistCustomers);

                    if(selC!= null){
                        blacklistCustomers.remove(selC);
                        CSVFile.removeRowByID(FileHandler.BLACKLIST_DB, String.valueOf(selC.getCustomerID()));            //remove from file

                        sPOS.cust.add(selC);
                        FileHandler.writeFile(FileHandler.CUSTOMER_DB, selC.toCSV());
                    }
                    else
                        System.out.println("No Blacklisted Customer Selected");
                    break;
                    
                case 3:
                    displayBlackList(blacklistCustomers);
                    break;
                    
                case 4:
                    return;
            }
        }while(sel != 4);
    }
    
    public void transactionManagment(){
    
        Scanner sc = new Scanner(System.in);
        
        StationaryPOS sPOS = StationaryPOS.getInstance();
        
        int sel = 0;
        do{
            String[] s = {"Transaction Management", "All Transactions","Outstanding Transactions", "Complete Transactions","Exit"};
            sel = Menu.menu(s);

            switch(sel){
                case 1:
                    sPOS.viewAllTransactions();
                    break;

                case 2:
                    for(Transaction t: sPOS.getTransactionType(sPOS.getTransactionList(), false)){
                        StationaryPOS.viewTransaction(t);
                    }
                    
                    String selection = "";
                    do{
                        System.out.print("Make Payment? (y/n):");
                        selection = sc.nextLine();
                    }while(!"y".equals(selection) && !"n".equals(selection));
                    
                    if("y".equals(selection)){
                        ArrayList<Transaction> tList = sPOS.getTransactionList();
                        
                        if(!tList.isEmpty()){
                            ArrayList<Transaction> uncompleteTList = sPOS.getTransactionType(tList, false);
                            
                            if(!uncompleteTList.isEmpty()){
                                Transaction t = (Transaction)Tools.objectSelection(uncompleteTList);  //get uncomplete transactions
                                
                                if(t != null){
                                    sPOS.salesPayment(t);
                                    StationaryPOS.viewTransaction(t);
                                }
                                else
                                    System.out.println("No Transaction Selected");
                            }
                            else
                                System.out.println("No Uncomplete Transactions");
                        }
                        else
                            System.out.println("No Transactions");
                    }
                    
                    break;

                case 3:
                    for(Transaction t: sPOS.getTransactionType(sPOS.getTransactionList(), true)){
                        StationaryPOS.viewTransaction(t);
                    }                    
                    break;
                    
                case 4:
                    break;
            }  
        }while(sel != 4);
    }
    
    public void inventoryManagement(){
        Scanner sc = new Scanner(System.in);
        
        StationaryPOS sPOS = StationaryPOS.getInstance();
        
        int sel = 0;
        do{
            String[] s = {"Inventory Management", "Add Item", "Modify Item", "Remove Item", "Display All Items", "Exit"};
            sel = Menu.menu(s);

            switch(sel){
                case 1:
                    sPOS.addItem();
                    break;

                case 2:
                    ArrayList<Item> iList = sPOS.inv.getItemList();
                    if(!iList.isEmpty()){
                        Item i = ((Item)Tools.objectSelection(iList));
                        if(i != null)
                            sPOS.modifyItem(i);
                        else
                            System.out.println("No Item Selected");
                    }
                    break;

                case 3:
                    sPOS.inv.removeItem((Item)Tools.objectSelection(sPOS.inv.getItemList()));
                    break;
                    
                case 4:
                    sPOS.viewAllItems();
                    break;
                    
                case 5:
                    break;
            }  
        }while(sel != 5);
    }
    
    public void itemSaleAnalysis(){
        StationaryPOS sPOS = StationaryPOS.getInstance();
        
        HashMap<Item, Integer> itemSoldQty = new HashMap<>();
        
        for (Sales sales : sPOS.sales) {
            for(SoldItem i: sales.getSoldItems()){
                if(!itemSoldQty.containsKey(i.getSoldItem())){
                    itemSoldQty.put(i.getSoldItem(), i.getQuantity());
                }
                else{
                    itemSoldQty.put(i.getSoldItem(), itemSoldQty.get(i.getSoldItem()) + i.getQuantity());
                }
            }
        }
        
        System.out.println("\nItem Sale Analysis\n"
                        +  "------------------");
        System.out.printf("%-10s%-30s%-10s%s\n","Item ID", "Item Name", "Quantity", "Total Sale");
        itemSoldQty.forEach((item, total) -> System.out.printf("%-10d%-30s%-10d%.2f\n", item.getItemID(), item.getItemName(),
                total, item.getPrice()*total));
    }
    
    public void customerSpendingAnalysis() {
        
        StationaryPOS sPOS = StationaryPOS.getInstance();
        
//        ArrayList<SoldItem> soldItemList = new ArrayList<>();
        double totalPoints = 0;
        
        HashMap<Customer, Double> customerTotal = new HashMap<>();
        double totalSpend = 0;
        double nonCustSpending = 0;
        
        for (Sales sales : sPOS.sales) {
//            soldItemList.addAll(sales.getSoldItems());
            totalPoints += (sales.calculatePoints());
            totalSpend += sales.calculateTotal();
            
            if (sales.getCustomer() != null) {
                if(!customerTotal.containsKey(sales.getCustomer()))     //if key doesnt exist 
                     // Update the existing spending value by adding the subtotal of the current sale
                    customerTotal.put(sales.getCustomer(),sales.calculateTotal());
                
                else{
                    //update value of customer if customer already exists in HashMap
                    customerTotal.put(sales.getCustomer(), customerTotal.get(sales.getCustomer()) + sales.calculateTotal());
                }
            } 
            else{
                    nonCustSpending += sales.calculateTotal();
            }
            
        }
        
        System.out.println("\nCustomer Spending Analysis\n"
                        +  "--------------------------");
        System.out.println("Total Spend By All: RM " + totalSpend);
        System.out.printf("%-10s%-20s%-15s%s\n", "ID", "Name", "Role", "Total Spent");
        customerTotal.forEach((cust, total) -> System.out.printf("%-10d%-20s%-15s%.2f\n",((Customer)cust).getCustomerID(), 
                ((Customer)cust).getName(),((Customer)cust).getRole(), total));
        System.out.println("\nTotal Spent by Non-Customer: " + nonCustSpending);
    }
    
    public static void viewSales() {
        StationaryPOS sPOS = StationaryPOS.getInstance();

        for(Sales s: sPOS.sales)
            System.out.println(s.toFormattedString()+ '\n');
    }
    
    public void displayBlackList(ArrayList<Customer> blacklistCustomers) {
        System.out.println("\n------------------------------------------------------------------------------------------------");
        System.out.println("\t\t\t\t\tBlacklisted Customer");
        System.out.println(  "------------------------------------------------------------------------------------------------");
        if (blacklistCustomers.size() == 0) {
            System.out.println("No blacklisted customer!");
            System.out.println("------------------------------------------------------------------------------------------------\n");
            return;
        }
        for (Customer bC : blacklistCustomers){
            System.out.println(bC.toFormattedString());
        }
        System.out.println("------------------------------------------------------------------------------------------------\n");
    }
    
    @Override
    public String toFormattedString(){
        return String.format(STRING_FORMAT,"Management",username, password, name, phoneNumber);
    }
    
    public String toCSV(){
        return "Management," + super.toCSV();
    }
}
