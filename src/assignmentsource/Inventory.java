/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignmentsource;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Tan Jee Schuan
 */
public class Inventory{
    
    List<Item> itemList = new ArrayList<Item>();
    
    //constructor

    Inventory()
    {
        
    }
    
    public void addItem(int barcode, String itemName,int quantity)
    {
        Item newItem = new Item(barcode, itemName, quantity);
        itemList.add(newItem);
    }
    
    public Item getItem(int barcode)
    {
        for (Item var: itemList)
        {
            if (barcode == var.barcode)
                return var;
        }
        
        return null;
    }
    
    public List getItem()
    {
        return itemList;
    }
}
