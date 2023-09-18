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

    public double getPrice(){
        return this.soldItem.getPrice();
    }

    public double getSubTotal(){
        return getPrice() * quantity;
    }

//    public double getSubTotalByRole(String role){
//        return Tools.getItemPriceByRole(this, role) * quantity;
//    }

    public void displaySoldItem(){
        System.out.printf("%-10d%-20s%-10d%-10.2f%-15.2f\n", soldItem.getItemID(), soldItem.getItemName(), quantity, getPrice(), getSubTotal());
    }
    
    @Override
    public String toString(){
        return String.format("%-10d%-20s%-10d%-10.2f%-15.2f\n", soldItem.getItemID(), soldItem.getItemName(), quantity, getPrice(), getSubTotal());
    }

}
