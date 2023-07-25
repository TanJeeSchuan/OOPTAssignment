/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignmentsource;

/**
 *
 * @author Tan Jee Schuan
 */
public class Item {
    public int barcode;
    public String itemName;
    public int quantity;
    
    Item(int barcode, String itemName,int quantity)
    {
        this.barcode = barcode;
        this.itemName = itemName;
        this.quantity = quantity;
    }
    
    public void modifyQuantity(int q)
    {
        this.quantity += q;
    }
    
    public void setQuantity(int q)
    {
        this.quantity = q;
    }
}
