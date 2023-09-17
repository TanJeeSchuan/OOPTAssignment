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
public class StationaryPOS {
    public static void main(String[] args) {
        Inventory inv = new Inventory(Init.initItems());
//        Item newItem = new Item(123 ,"01231","Test Item", 69, 20.00, 19.50);
//        
//        inv.addItem(newItem);

//        System.out.println(inv.getItemList().get(0).toString());
        System.out.println(Init.initItems());
        
        
        
//        ArrayList<Item> items;
//        items = inv.getItemList();
//        
//        inv.removeItem(items.get(2));
        inv.updateFile();
    }
}
