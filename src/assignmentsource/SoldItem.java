package assignmentsource;


public class SoldItem {
    private Item soldItem;
    private int quantity;

    public SoldItem(Item soldItem, int quantity){
        this.soldItem = soldItem;
        this.quantity = quantity;
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
        return Tools.getCustomerTypePrice(this);
    }

    public double getSubTotal(){
        return Tools.getCustomerTypePrice(this) * quantity;
    }

    public double getSubTotalByRole(String role){
        return Tools.getItemPriceByRole(this, role) * quantity;
    }

    public void displaySoldItem(){
        System.out.printf("%-10d%-20s%-10d%-10.2f%-15.2f\n", soldItem.getItemID(), soldItem.getItemName(), quantity, getPrice(), getSubTotal());
    }

}
