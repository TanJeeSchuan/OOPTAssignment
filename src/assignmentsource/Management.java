/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignmentsource;

import java.util.ArrayList;
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
        
        StationaryPOS sPOS = StationaryPOS.getInstance();
        
        int menuSel = 0;
        do{
            System.out.print("\nManagement\n\n1. Manage Transactions\n2. Manage Customers\n3. Manage Inventory\n4. Logout\n\n");
            switch(menuSel = sc.nextInt())
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
                    return true;
            }  
        }while(menuSel != 4);
        
        return false;
    }
    
    public void customerManagement(){
        Scanner sc = new Scanner(System.in);
        
        StationaryPOS sPOS = StationaryPOS.getInstance();
        
        int sel = 0;
        do{
            System.out.print("\nCustomer Management\n\n1. Add Customer\n2. Modify Customer\n3. Blacklist Customer\n4. Display All Customers\n5. Exit\n\n");
            switch(sel = sc.nextInt())
            {
                case 1:
                    sPOS.addCustomer();
                    break;

                case 2:
                    Customer c = (Customer)Tools.objectSelection(sPOS.cust);
                    sPOS.modfiyCustomer(c);
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
            System.out.print("\nBlacklist Management\n1. Add Customer to blacklist\t\t2. Remove Customer from blacklist\nSelection: ");
            sel = sc.nextInt();
        }while(!(sel >= 1) && !(sel <= 2));

        if(sel == 1){
            Customer c = (Customer)Tools.objectSelection(sPOS.cust);
            
            if(c != null){
                sPOS.removeCustomer(c);            //remove from file
                
                blacklistCustomers.add(c);
                CSVFile.writeFile(FileHandler.BLACKLIST_DB, c.toCSV());
            }
            else
                System.out.println("No Customer Selected");
        }   
        else if(sel == 2){
            Customer c = (Customer)Tools.objectSelection(blacklistCustomers);
            
            if(c != null){
                blacklistCustomers.remove(c);
                CSVFile.removeRowByID(FileHandler.BLACKLIST_DB, String.valueOf(c.getCustomerID()));            //remove from file
                
                sPOS.cust.add(c);
                FileHandler.writeFile(FileHandler.CUSTOMER_DB, c.toCSV());
            }
            else
                System.out.println("No Blacklisted Customer Selected");
        }
    }
    
    public void transactionManagment(){
    
        Scanner sc = new Scanner(System.in);
        
        StationaryPOS sPOS = StationaryPOS.getInstance();
        
        int sel = 0;
        do{
            System.out.print("\nTransaction Management\n\n1. All Transactions\n2. Outstanding Transactions\n3. Complete Transactions\n4. Exit\n\n");
            switch(sel = sc.nextInt())
            {
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
                        selection = sc.next();
                    }while(!"y".equals(selection) && !"n".equals(selection));
                    
                    if("y".equals(selection)){
                        Transaction t = (Transaction)Tools.objectSelection(sPOS.getTransactionType(sPOS.getTransactionList(), false));
                        sPOS.salesPayment(t);
                        StationaryPOS.viewTransaction(t);
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
            System.out.print("\nInventory Management\n\n1. Add Item\n2. Modify Item\n3. Remove Item\n4. Display All Items\n5. Exit\n\n");
            switch(sel = sc.nextInt())
            {
                case 1:
                    sPOS.addItem();
                    break;

                case 2:
                    Item i = ((Item)Tools.objectSelection(sPOS.inv.getItemList()));
                    sPOS.modifyItem(i);
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
    
    public static void viewSales() {
        StationaryPOS sPOS = StationaryPOS.getInstance();

        for(Sales s: sPOS.sales)
            System.out.println(s.toFormattedString()+ '\n');
    }
    
    @Override
    public String toFormattedString(){
        return String.format(STRING_FORMAT,"Management",username, password, name, phoneNumber);
    }
    
    public String toCSV(){
        return "Management," + super.toCSV();
    }
}
