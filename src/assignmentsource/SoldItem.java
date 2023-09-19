package assignmentsource;


public class SoldItem {
    private int saleID;
    private Item soldItem;
    private int quantity;

    public SoldItem(int saleID ,Item soldItem, int quantity){
        this.saleID = saleID;
        this.soldItem = soldItem;
        this.quantity = quantity;
    }
    
    //creating from user input
    public SoldItem(Sales sale, Item soldItem, int quantity){
        this.saleID = sale.getSaleID();
        this.soldItem = soldItem;
        this.quantity = quantity;
    }
    
    public int getSaleID(){
        return saleID;
    }

    public Item getSoldItem(){
        return soldItem;
    }

    public int getQuantity(){
        return quantity;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
    
    public double getBulkPrice(){
        return this.soldItem.getBulkPrice();
    }
    
    public double getBulkSubTotal(){
        return getBulkPrice() * quantity;
    }

    public double getPrice(){
        return this.soldItem.getPrice();
    }

    public double getSubTotal(){
        return getPrice() * quantity;
    }
    
    public void writeToFile(){
        FileHandler.writeFile(FileHandler.SOLD_ITEM_DB, toCSV());
    }

//    public double getSubTotalByRole(String role){
//        return Tools.getItemPriceByRole(this, role) * quantity;
//    }
    
    public String toCSV(){
        return String.valueOf(saleID) + "," + String.valueOf(soldItem.getItemID() + "," + String.valueOf(quantity));
    }
    
    @Override
    public String toString(){
        return String.format("%-10d%-20s%-10d%-10.2f%15.2f", soldItem.getItemID(), soldItem.getItemName(), quantity, getPrice(), getSubTotal());
    }
}
