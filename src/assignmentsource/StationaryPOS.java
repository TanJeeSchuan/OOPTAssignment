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

//singleton
public class StationaryPOS {    
    //set as null first
    private static StationaryPOS sPOS = null;
    
    public Inventory inv;
    public ArrayList<Sales> sales;  
    public ArrayList<Customer> cust;

    //constuction set as private, initialisation controlled by method in class
    private StationaryPOS()
    {
        ArrayList<Transaction> transaction;
        ArrayList<Item> itemList;
        ArrayList<SoldItem> soldItemList;
        
        itemList = Init.initItems();
        
        soldItemList = Init.initSoldItems(itemList);
        inv = new Inventory(itemList);
        cust = Init.initCustomers();
        transaction = Init.initTransactions();
        
        sales = Init.initSales(cust, transaction, soldItemList);
    }
    
    public static synchronized StationaryPOS getInstance()
    {
        //if has not be initialse, inititalse then return object. Else, return last object
        if (sPOS == null)
            sPOS = new StationaryPOS();
        return sPOS;
    }
    
    public Sales addSales(){
        Scanner sc = new Scanner(System.in);
        
        Customer saleCust = null;
        Item soldItem = null;
        int soldQty = 0;
        
        ArrayList<SoldItem> soldItemsList = new ArrayList<>();
        
        //initialse list of SoldItem to use in Sales constructor
        while(true){
            System.out.println("");
            soldItem = ((Item)Tools.objectSelection(sPOS.inv.getItemList()));  //get Selectable object. Cast Selectable to Item class
            if(soldItem == null)
                break;
            
            if(soldItem.getQuantity() > 0){
                do{
                    System.out.printf("\nEnter sold item quantity (< %d) : ", soldItem.getQuantity());
                    soldQty = Menu.getIntegerInput();
                }while(soldQty < 1 || soldQty > soldItem.getQuantity());
            }
            else
                System.out.println("Item Out Of Stock");
            
            SoldItem i = new SoldItem(Sales.newSaleID() ,soldItem, soldQty);
            inv.updateItemQty(i);
            soldItemsList.add(i);
            
            boolean cont = true;
            do{
                System.out.print("\nContinue Sale? (y/n): ");
                
                String sel = sc.next();
                
                if ("y".equals(sel))
                    break;
                else if("n".equals(sel))
                {
                    cont = false;
                    break;
                }
                else
                    System.out.println("Enter Valid Input");
            }while(true);
            
            if(!cont)
                break;
        }
        
        //selection output
        saleCust = ((Customer)Tools.objectSelection(sPOS.cust));  //get class that implements Selectable interface. Cast Selectable to Customer class

        int installmentLength = 0;
        
        int selected = 0;
        do{
            System.out.println("\nPayment Type");
            System.out.println("1)Installment\t\t2)One Time Payment");
            selected = Menu.getIntegerInput();
        }while(selected != 1 && selected != 2);

        if (selected == 1){
            System.out.print("\nInstallment length: ");
            installmentLength = Menu.getIntegerInput();
        }
        else if(selected == 2)
            installmentLength = 0;
        
        Sales newSale = new Sales(saleCust, soldItemsList, installmentLength);
        
        if(newSale.getCustomer() != null){
            if(!"wholesaler".equals(newSale.getCustomer().getRole())){
                while(true){
                    System.out.printf("\nEnter amount of points to redeem (Current Points: %d): ", newSale.getCustomer().getCurrentPoints());
                    int redeemedPoints = Menu.getIntegerInput();
                    if(newSale.redeemPoints(redeemedPoints))
                        break;
                }
            }
            else{
                System.out.println("Wholesaler not eligible for discount");
            }
        }
        
        sPOS.sales.add(newSale);
        
//        System.out.println(newSale.getTransaction().toFormattedString());
        return newSale;
    }
    
    public void salesPayment(Transaction transaction){
        Scanner sc = new Scanner(System.in);
        
        System.out.println("");
        viewTransaction(transaction);
        
        if(!transaction.paymentFinished()){
            if(transaction.isInstallment()){
                int selected = 0;
                do{
                    System.out.print("Installment Payment:\n"
                    +"1)Monthly Payment\t\t2)Pay Amount\n\n");

                    selected = Menu.getIntegerInput();
                }while(selected != 1 && selected != 2);

                if (selected == 1){
                    transaction.payMonthly();
                    System.out.printf("\nBalance: %.2f", transaction.getBalanceLeft());
                }
                else if (selected == 2){                    
                    double payAmount;
                    do{
                        System.out.println("Enter amount paid: ");
                        payAmount = Menu.getDoubleInput();
                    }while(payAmount < transaction.getBalanceLeft());
                    
                    System.out.printf("\nBalance: %.2f", transaction.getBalanceLeft());
                }
            }
            else{
                double payAmount;
                do{
                    System.out.print("Enter amount paid: ");
                    payAmount = Menu.getDoubleInput();
                }while(payAmount < transaction.getBalanceLeft());

                System.out.printf("Balance: %.2f",  transaction.pay(payAmount));
                
            }
        }
        else{
            System.out.println("Transaction finialised\n");
            return;
        }
        
        System.out.println("");
        viewTransaction(transaction);
        if(transaction.paymentFinished())
            System.out.println("Transaction finialised\n");
    }
    
    public Customer addCustomer(){
        Scanner sc = new Scanner(System.in);
        String name;
        String phoneNumber;
        String role = null;
        
        System.out.print("\nEnter Customer Name: ");
        name = sc.nextLine();
        System.out.print("\nEnter Phone Number: ");
        phoneNumber = sc.nextLine();

        int selection = 0;
        do{
            System.out.print("\nSelect Role:\n");
            System.out.println("1)Retailer\t\t2)Wholesaler\n");

            selection = Menu.getIntegerInput();
        }while(selection != 1 && selection != 2);

        switch(selection){
            case 1:
                role = "retailer";
                break;
            case 2:
                role = "wholesaler";
        }

        Customer newCust = new Customer(name,phoneNumber,role);
        cust.add(newCust);
        return newCust;
    }
    
    public void modfiyCustomer(Customer cust){
        Scanner sc = new Scanner(System.in);
        
        System.out.println("\n\nSelected Customer:");
        System.out.println(Customer.FORMAT_HEADER);
        System.out.println(cust.toFormattedString());
        
        int selection = 0;
        while(true){
            do{
                String[] s = {"Customer Modification", "Customer Name", "Phone Number", "Current Points", "Role", "Exit"};
                selection = Menu.menu(s);

                switch(selection){
                    case 1:
                        cust.setName(sc.next());
                        break;

                    case 2:
                        cust.setPhoneNumber(sc.next());
                        break;

                    case 3:
                        cust.setCurrentPoints(Menu.getIntegerInput());
                        break;

                    case 4:
                        int sel = 0;
                        do{
                            System.out.printf("\nRetailer or Wholesaler (Current Role: %s)\n1. Retailer\t\t2. Wholesaler\nSelection: ", cust.getRole());
                            sel = Menu.getIntegerInput();
                        }while(!(sel >= 1) && !(sel <= 2));
                        
                        if(sel == 1)
                            cust.setRole("retailer");
                        else if(sel == 2)
                            cust.setRole("wholesaler");
                        
                        break;

                    case 5:
                        break;
                }
            }while(!(selection >= 1) && !(selection <= 5));
            
            if (selection == 5)
                break;
        }
    }
    
    public void removeCustomer(Customer cust){
        sPOS.cust.remove(cust);
        CSVFile.removeRowByID(FileHandler.CUSTOMER_DB, String.valueOf(cust.getCustomerID()));
    }
    
    public Item addItem(){
        String itemName;
        String barcode = "";
        int quantity;
        double price;
        double bulkPrice;
        
        Scanner sc = new Scanner(System.in);
        
        System.out.print("\nEnter Item Name: ");
        itemName = sc.next();
        
        do{
        System.out.print("\nEnter Valid Barcode: ");
        barcode = sc.next();
        }while(barcode.matches(".*[a-zA-Z]+.*"));
        
        System.out.print("\nEnter Quantity: ");
        quantity = Menu.getIntegerInput();
        
        System.out.print("\nEnter Normal Price: ");
        price = Menu.getDoubleInput();
        
        System.out.print("\nEnter Bulk Price: ");
        bulkPrice = Menu.getDoubleInput();
        
        Item i = new Item(itemName, barcode, quantity, price, bulkPrice);
        inv.addItem(i);
        return i;
    }
    
    public void modifyItem(Item item){
        Scanner sc = new Scanner(System.in);
        
        System.out.println("\n\nSelected Item:");
        System.out.println(Item.FORMAT_HEADER);
        System.out.println(item.toFormattedString());
        
        int selection = 0;
        while(true){
            do{
                String[] s = {"Select field to modify:", "Barcode","Item Name", "Quantity", "Price", "Bulk Price", "Exit"};
                selection = Menu.menu(s);
                switch(selection){
                    case 1:
                        String newBarcode = "";
                        do{
                            System.out.print("\nEnter Valid Barcode: ");
                            newBarcode = sc.next();
                        }while(newBarcode.matches(".*[a-zA-Z]+.*"));
                        item.setBarCode(newBarcode);
                        break;

                    case 2:
                        item.setItemName(sc.next());
                        break;

                    case 3:
                        item.setQuantity(Menu.getIntegerInput());
                        break;

                    case 4:
                        item.setPrice(Menu.getDoubleInput());
                        break;

                    case 5:
                        item.setBulkPrice(Menu.getDoubleInput());
                        break;

                    case 6:
                        break;
                }
            }while(!(selection >= 1) && !(selection <= 6));
            
            if (selection == 6)
                break;
        }
    }
    
    public void updateItemQuantity(ArrayList<SoldItem> soldItemList){
        inv.updateItemQty(soldItemList);
    }
    
    public void viewAllItems(){
        System.out.println(Item.FORMAT_HEADER);
        for(Item i: inv.getItemList()){
            System.out.println(i.toFormattedString());
        }
    }
    
    public ArrayList<Transaction> getTransactionType(ArrayList<Transaction> transactionList, boolean isCompleted){
        ArrayList<Transaction> tList = new ArrayList<>();
        
        for(Transaction t: transactionList){
            //if is completed
            if(isCompleted){
                if(t.paymentFinished())
                    tList.add(t);
            }
            //if is not completed
            else{
                if(!t.paymentFinished())
                    tList.add(t);
            }
        }
        
        return tList;
    }
    
    public ArrayList<Transaction> getTransactionList(){
        ArrayList<Transaction> tList = new ArrayList<>();
        System.out.println("");
        for (Sales s: sales){
            Transaction t = null;
            t = s.getTransaction();
            if(t != null)
                tList.add(t);
//            else
//                System.out.printf("Sale ID %d has no transaction record!\n\n", s.getSaleID());
        }
        return tList;
    }
    
    public void viewAllTransactions(){
        System.out.println("");
        
        for (Sales s: sales){
            Transaction t = null;
            t = s.getTransaction();
            
            if(t != null)
                viewTransaction(t);
            else
                System.out.printf("Sale ID %d has no transaction record!\n\n", s.getSaleID());
        }
    }
    
    public static void viewTransaction(Transaction transaction){
        System.out.printf("%-20s%-20s\n", "Transaction ID:", transaction.getTransactionID());
        System.out.printf("%-20s%-20.2f\n", "Total Amount:", transaction.getTotalAmount());
        if (!transaction.isInstallment()) {
            System.out.printf("%-20s%-20s\n", "Payment Type:", "One Time Payment");
        } else {
            System.out.printf("%-20s%-20s\n", "Payment Type:", "Installment");
            System.out.printf("%-20s%-20s\n", "Installment Period:", transaction.getInstallmentTimes()+" months");
            System.out.printf("%-20s%-20s\n", "Time Left:", transaction.getTimesLeft());
            System.out.printf("%-20s%-20s\n", "Balance Left:", transaction.getBalanceLeft());
        }
        System.out.println("-----------------------------------");
    }
}
