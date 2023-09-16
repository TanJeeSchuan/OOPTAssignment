package assignmentsource;

import java.util.List;

public interface ISales {
    int getSaleID();
    void setSaleID(int saleID);
    String getTimeOfSale();
    void setTimeOfSale(String timeOfSale);
    int getCustomerID();
    void setCustomerID(int customerID);
    Customer getCustomer();
    void setCustomer(Customer customer);
    List<Integer> getSoldItemsID();
    void setSoldItemsID(List<Integer> soldItemsID);
    List<SoldItem> getSoldItems();
    void setSoldItems(List<SoldItem> soldItems);
    void addSoldItemID(int soldItemID);
    void addSoldItem(SoldItem soldItem);
    List<Integer> getSoldItemsQuantity();
    void setItemsQuantity(List<Integer> itemsQuantity);
    void addItemQuantity(int itemQuantity);
    double getTotalPrice();
    int getPoints(double totalPrice);
    String toString();
    void storeSale();
}
