package assignmentsource;

import java.util.ArrayList;
import java.util.List;

public class Sales implements ISales{

    private int saleID;
    private String timeOfSale;
    private int customerID;
    private Customer customer;
    private List<Integer> soldItemsID;
    private List<SoldItem> soldItems;
    private List<Integer> soldItemsQuantity;

    public Sales(){
        this.saleID = Tools.getNewID(FileHandler.SALES_DB);      //不错
        this.timeOfSale = Tools.getCurrentDateTime();
        this.customerID = 0;
        this.soldItemsID = new ArrayList<>();
        this.soldItems = new ArrayList<>();
        this.soldItemsQuantity = new ArrayList<>();
    }

    public Sales(int saleID) {
        List<String> saleInfo = FileHandler.getRowByMainID(FileHandler.SALES_DB, String.valueOf(saleID));
        this.saleID = saleID;
        this.timeOfSale = saleInfo.get(1);
        this.customerID = Integer.parseInt(saleInfo.get(2));
        this.soldItemsID = Tools.stringToIntList(saleInfo.get(3));
        this.soldItems = new ArrayList<>();
        this.soldItemsQuantity = Tools.stringToIntList(saleInfo.get(4));
        initSoldItems();
    }

    public Sales(int saleID, String timeOfSale, int customerID, List<Integer> soldItemsID, List<Integer> soldItemsQuantity) {
        this.saleID = saleID;
        this.timeOfSale = timeOfSale;
        this.customerID = customerID;
        this.customer = Tools.getCustomerByID(customerID);
        this.soldItemsID = soldItemsID;
        this.soldItemsQuantity = soldItemsQuantity;
        this.soldItems = new ArrayList<>();
        initSoldItems();
    }

    private void initSoldItems(){
        for (int i = 0; i < soldItemsID.size(); i++) {
            this.soldItems.add(new SoldItem(
                    new Item(soldItemsID.get(i)), soldItemsQuantity.get(i))
            );
        }
    }

    public int getSaleID() {
        return saleID;
    }

    public void setSaleID(int saleID) {
        this.saleID = saleID;
    }

    public String getTimeOfSale() {
        return timeOfSale;
    }

    public void setTimeOfSale(String timeOfSale) {
        this.timeOfSale = timeOfSale;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
        this.customerID = customer.getCustomerID();
    }

    public List<Integer> getSoldItemsID() {
        return soldItemsID;
    }

    public void setSoldItemsID(List<Integer> soldItemsID) {
        this.soldItemsID = soldItemsID;
    }

    public List<SoldItem> getSoldItems() {
        return soldItems;
    }

    public void setSoldItems(List<SoldItem> soldItems) {
        this.soldItems = soldItems;
    }

    public void addSoldItemID(int soldItemID){
        this.soldItemsID.add(soldItemID);
    }

    public void addSoldItem(SoldItem soldItem){
        this.soldItems.add(soldItem);
    }

    public List<Integer> getSoldItemsQuantity() {
        return soldItemsQuantity;
    }

    public void setItemsQuantity(List<Integer> soldItemsQuantity) {
        this.soldItemsQuantity = soldItemsQuantity;
    }

    public void addItemQuantity(int soldItemQuantity){
        this.soldItemsQuantity.add(soldItemQuantity);
    }

    public double getTotalPrice() {
        double totalPrice = 0;
        for (SoldItem soldItem : soldItems) {
            totalPrice += soldItem.getSubTotal();
        }
        return totalPrice;
    }

    public double getSubTotalByRole() {
        double totalPrice = 0;
        for (SoldItem soldItem : soldItems) {
            totalPrice += soldItem.getSubTotalByRole(this.customer.getRole());
        }
        return totalPrice;
    }

    public int getPoints(double totalPrice) {
        return getCustomer() instanceof Retailer ? (int) (totalPrice) : 0;
    }

    public String toString() {
        return this.saleID + "," + this.timeOfSale + "," +
                this.customerID + "," + Init.intListToString(this.soldItemsID) + "," +
                Init.intListToString(this.soldItemsQuantity);
    }

    public void storeSale(){
        FileHandler.writeFile(FileHandler.SALES_DB, this.toString());
        for (SoldItem soldItem : soldItems) {
            Tools.generateSalesLog(
                    this.customer.getName(),
                    this.customer.getRole(),
                    soldItem.getSoldItem().getItemName(),
                    soldItem.getSoldItem().getQuantity()
            );
        }
    }

}
