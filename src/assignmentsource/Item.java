/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignmentsource;

/**
 *
 * @author Yeoh Ming Zhe
 */
public class Item {
    
    public static int itemID = 00001;
    public String barcode;
    public String itemName;
    public int quantity;
    public double price;
    
    
    Item(int itemID, String barcode, int quantity, double price, String itemName)
    {
        Item.itemID = itemID;
        this.barcode = barcode;
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
    }

    
    //getter
    public int getItemID() {
        return itemID;
    }

    public String getBarcode() {
        return barcode;
    }

    public String getItemName() {
        return itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    
    
    //setter

    public void setItemID(int itemID) {
        Item.itemID = itemID;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return getItemName() + "," + getItemID()+ "," + getQuantity() + "," + "RM " + getPrice() + "," + getBarcode(); 
    }

    

    
    
    
}
