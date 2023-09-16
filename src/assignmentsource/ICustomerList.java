package assignmentsource;

import java.util.List;

public interface ICustomerList {
    void addCustomer(Customer customer);
    void removeCustomer(Customer customer);
    Customer getCustomer(int customerID);
    Customer getCustomer(String phoneNumber);
    List<Customer> getCustomerList();
    void setCustomerList(List<Customer> customerList);
    boolean isIDExist(int customerID);
    boolean isEmpty();
    void simpleCustomerList();
    void viewCustomerList();
}
