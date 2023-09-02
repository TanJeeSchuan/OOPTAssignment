package assignmentsource;

/**
 *
 * @author Yeoh Ming Zhe
 */
public class Item {
    
    private int itemID;
    private String barcode;
    private String itemName;
    private int quantity;
    private double price;
    
    
    Item(int itemID, String barcode, int quantity, double price, String itemName)
    {
        this.itemID = itemID;
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
        this.itemID = itemID;
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
        return getItemID() + "," + getItemName()+ "," + getQuantity() + "," + "RM " + getPrice() + "," + getBarcode(); 
    }
    
}
