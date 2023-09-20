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
    
    //test
    private ArrayList<Transaction> transaction;
    private ArrayList<Item> itemList;
    private ArrayList<SoldItem> soldItemList;
    
    //constuction set as private, initialisation controlled by method in class
    private StationaryPOS()
    {
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
            
            do{
                System.out.printf("\nEnter sold item quantity (< %d) : ", soldItem.getQuantity());
                soldQty = sc.nextInt();
            }while(soldQty > soldItem.getQuantity());
            
            soldItemsList.add(new SoldItem(Sales.newSaleID() ,soldItem, soldQty));
            
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
        
        if(saleCust != null){
            int selected = 0;
            do{
                System.out.println("\nPayment Type");
                System.out.println("1)Installment\t\t2)One Time Payment\n");
                selected = sc.nextInt();
            }while(!(selected >= 1) && !(selected <= 2));

            if (selected == 1){
                System.out.print("Installment length: ");
                installmentLength = sc.nextInt();
            }
            else if(selected == 2)
                installmentLength = 0;
        }
        
        Sales newSale = new Sales(saleCust, soldItemsList, installmentLength);        
        sPOS.sales.add(newSale);
        
//        System.out.println(newSale.getTransaction().toFormattedString());
        return newSale;
    }
    
    public void salesPayment(Transaction transaction){
        Scanner sc = new Scanner(System.in);
        
        viewTransaction(transaction);
        
        if(transaction.paymentFinished()){
            if(transaction.isInstallment()){
                int selected = 0;
                do{
                    System.out.print("Installment Payment:\n"
                    +"1)Monthly Payment\t\t2)Pay Amount\n\n");

                    selected = sc.nextInt();
                }while(!(selected >= 1) && !(selected <= 2));

                if (selected == 1){
                    transaction.payMonthly();
                    System.out.println("Balance: " + transaction.getBalanceLeft());
                }
                else if (selected == 2){
                    System.out.print("Amount to pay: ");
                    
                    double payAmount;
                    do{
                        System.out.println("Enter amount paid: ");
                        payAmount = sc.nextDouble();
                    }while(payAmount < transaction.getBalanceLeft());
                    
                    System.out.println("\nBalance: "+transaction.pay(payAmount));
                }
            }
        }
        else{
            System.out.println("Transaction finialised\n");
        }
    }
    
    public Customer addCustomer(){
        Scanner sc = new Scanner(System.in);
        String name;
        String phoneNumber;
        String role = null;
        
        
        do{
            System.out.print("\nEnter Customer Name: ");
            name = sc.nextLine();
            System.out.print("\nEnter Phone Number: ");
            phoneNumber = sc.nextLine();
            
            int selection = 0;
            do{
                System.out.print("\nSelect Role:");
                System.out.println("1)Retailer\t\t2)Wholesaler\n");
                
                selection = sc.nextInt();
            }while(!(selection >= 1) && !(selection <=2));
            
            switch(selection){
                case 1:
                    role = "retailer";
                    break;
                case 2:
                    role = "wholesaler";
            }
            
            switch(selection){
                case 1:
                    role = "retailer";
                    break;
                case 2:
                    role = "wholesaler";
            }    
        
            String sel = "";
            do{
                System.out.print("\n\nContinue adding customer?(y/n):");
                sel = sc.next();
            }while(!("y".equals(sel)) && !("n".equals(sel)));
            
            if("n".equals(sel))
                break;
        }while(true);
        
        Customer newCust = new Customer(name,phoneNumber,role);
        cust.add(newCust);
        return newCust;
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
        quantity = sc.nextInt();
        
        System.out.print("\nEnter Normal Price: ");
        price = sc.nextDouble();
        
        System.out.print("\nEnter Bulk Price: ");
        bulkPrice = sc.nextDouble();
        
        Item i = new Item(itemName, barcode, quantity, price, bulkPrice);
        inv.addItem(i);
        return i;
    }
    
    public void viewItemsList(){
        System.out.println(Item.FORMAT_HEADER);
        for(Item i: inv.getItemList()){
            System.out.println(i.toFormattedString());
        }
    }
    
    public void viewTransactionList(){
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
        //System.out.printf("%-20s%-20s\n", "Datetime:", transaction.getSales().getTimeOfSale());
        //System.out.printf("%-20s%-20s\n", "Customer Name:", transaction.getCustomer().getName());
        System.out.printf("%-20s%-20s\n", "Total Amount:", transaction.getTotalAmount());
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
    
    public static void modifyItem(Item item){
        Scanner sc = new Scanner(System.in);
        
        System.out.println("\n\nSelected Item:");
        System.out.println(Item.FORMAT_HEADER);
        System.out.println(item.toFormattedString());
        
        int selection = 0;
        while(true){
            do{
                System.out.print("\nSelect field to modify:\n");
                System.out.print("1) Barcode\n2) Item Name\n3) Quantity\n4) Price\n5) Bulk Price\n6) Exit");
                System.out.print("\nSelection: ");
                selection = sc.nextInt();

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
                        item.setQuantity(sc.nextInt());
                        break;

                    case 4:
                        item.setPrice(sc.nextDouble());
                        break;

                    case 5:
                        item.setBulkPrice(sc.nextDouble());
                        break;

                    case 6:
                        break;
                }
            }while(!(selection >= 1) && !(selection <= 6));
            
            if (selection == 6)
                break;
        }
    }
//    public static void main(String[] args) {
//        
//        Inventory inv = new Inventory(Init.initItems());
//                
//        Item newItem = new Item(123 ,"01231","Test Item", 69, 20.00, 19.50);
//        
//        inv.addItem(newItem);
////        inv.itemSelection();
////        System.out.println(inv.itemSelection().toString());
////        inv.removeItem(inv.itemSelection());
////        
////        
////        ArrayList<String> oldRow = CSVFile.getRowByType(FileHandler.INVENTORY_DB, 1, "8718114712");
//////        System.out.println(inv.getItemList().get(0).toString());
////        System.out.println(oldRow);
////        
////        ArrayList<String> newRow = new ArrayList<String>(oldRow);
////        newRow.set(0, "2");
////        
////        CSVFile.updateDataByRow(FileHandler.INVENTORY_DB, oldRow , newRow);
////        
////        System.out.println(CSVFile.getRowByType(FileHandler.INVENTORY_DB, 1, "8718114712"));
//        
////        ArrayList<Item> items;
////        items = inv.getItemList();
////        
////        inv.removeItem(123);
//        
////        inv.updateFile();
//    }
}
