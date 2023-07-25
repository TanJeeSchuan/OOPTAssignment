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
    public double price;
    
    Item(int barcode, String itemName,int quantity, double price)
    {
        this.barcode = barcode;
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
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
