/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignmentsource;

import java.util.ArrayList;

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
    public ArrayList<Transaction> transaction;
    
    //test
    public ArrayList<Item> itemList;
    public ArrayList<SoldItem> soldItemList;
    
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
