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
    
    public void addSales()
    {
        Scanner sc = new Scanner(System.in);
        
        Customer saleCust = null;
        Item soldItem = null;
        int soldQty = 0;
        
        ArrayList<SoldItem> soldItemsList = new ArrayList<>();
        
        while(true){            
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
                System.out.print("Continue Sale? (y/n): ");
                
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
        
        saleCust = ((Customer)Tools.objectSelection(sPOS.cust));  //get class that implements Selectable interface. Cast Selectable to Customer class
        
        Sales newSale = new Sales(saleCust, soldItemsList);        
        sPOS.sales.add(newSale);
        
        System.out.println(newSale.toFormattedString());
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
