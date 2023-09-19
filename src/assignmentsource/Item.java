package assignmentsource;
import java.util.List;

public class Item implements Selectable{
    
    public final static String[] FILE_HEADER = Inventory.FILE_HEADER;
    public final static String STRING_FORMAT = "%-5d%-12s%-40s%5d%15.2f%15.2f";     //"itemID,barCode,itemName,quantity,price,bulkPrice"
    public final static String FORMAT_HEADER = String.format("%-5s%-12s%-40s%-15s%-15s%s", "ID", "barCode", "itemName", "quantity", "price", "bulkPrice");

    private int itemID;
    private String barCode;
    private String itemName;
    private int quantity;
    private double price;
    private double bulkPrice;

    public Item(int itemID) {
        List<String> itemInfo = CSVFile.getRowByMainID(FileHandler.INVENTORY_DB, String.valueOf(itemID));
        this.itemID = itemID;
        this.barCode = itemInfo.get(1);
        this.itemName = itemInfo.get(2);
        this.quantity = Integer.parseInt(itemInfo.get(3));
        this.price = Double.parseDouble(itemInfo.get(4));
        this.bulkPrice = Double.parseDouble(itemInfo.get(5));
    }

    public Item(String itemName, int quantity, double price, double bulkPrice) {
        this.itemID = Tools.getNewID(FileHandler.INVENTORY_DB);
        this.barCode = Tools.generateBarCode();
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
        this.bulkPrice = bulkPrice;
    }

    public Item(int itemID, String barCode, String itemName, int quantity, double price, double bulkPrice) {
        this.itemID = itemID;
        this.barCode = barCode;
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
        this.bulkPrice = bulkPrice;
    }
    
    public Item(String[] itemData)
    {
        if (itemData.length != 6)
            throw new IllegalArgumentException("Item initialisation array length must be 6");
        
        else
        {
            this.itemID = Integer.parseInt(itemData[0]);
            this.barCode = itemData[1];
            this.itemName = itemData[2];
            this.quantity = Integer.parseInt(itemData[3]);
            this.price = Double.parseDouble(itemData[4]);
            this.bulkPrice = Double.parseDouble(itemData[5]);
        }
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getBulkPrice() {
        return bulkPrice;
    }

    public void setBulkPrice(double bulkPrice) {
        this.bulkPrice = bulkPrice;
    }

    public void updatePrice(double price, int updateChoice) {
        if (updateChoice == 1) updatePrice(price);
        else if (updateChoice == 2)  updateBulkPrice(price);
    }

    public void updatePrice(double price) {
        this.price = price;
        CSVFile.updateDataByID(FileHandler.INVENTORY_DB, String.valueOf(itemID), "price", String.valueOf(price));
    }

    public void updateBulkPrice(double bulkPrice) {
        this.bulkPrice = bulkPrice;
        CSVFile.updateDataByID(FileHandler.INVENTORY_DB, String.valueOf(itemID), "bulkPrice", String.valueOf(bulkPrice));
    }

    public void updateQuantity(int quantity) {
        this.quantity += quantity;
        CSVFile.updateDataByID(FileHandler.INVENTORY_DB, String.valueOf(itemID), "quantity", String.valueOf(this.quantity));
    }

    @Override
    public String toString() {
        return itemID + "," + barCode + "," + itemName + "," + quantity + "," + price + "," + bulkPrice;
    }
    
    //returns string suitable for display
    @Override
    public String toFormattedString(){
        return String.format(STRING_FORMAT, itemID, barCode, itemName, quantity,price, bulkPrice);
    }
    
    @Override
    public String[] getFILEHEADER() {
        return FILE_HEADER;
    }
    @Override
    public String getSTRINGFORMAT() {
        return STRING_FORMAT;
    }
    @Override
    public String getFORMATHEADER() {
        return FORMAT_HEADER;
    }
}
