package assignmentsource;

import java.util.List;

public class CustomerList implements ICustomerList {
    private List<Customer> customerList;

    public CustomerList(){
        this.customerList = Init.initCustomers();
    }

    public Customer getCustomer(int customerID){
        for(Customer customer : customerList){
            if(customer.getCustomerID() == customerID){
                return customer;
            }
        }
        return null;
    }

    public Customer getCustomer(String phoneNumber){
        for(Customer customer : customerList){
            if(customer.getPhoneNumber().equals(phoneNumber)){
                return customer;
            }
        }
        return null;
    }

    public void addCustomer(Customer customer){
        customerList.add(customer);
    }

    public void removeCustomer(Customer customer){
        customerList.remove(customer);
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    public boolean isIDExist(int customerID){
        for(Customer customer : customerList){
            if(customer.getCustomerID() == customerID){
                return true;
            }
        }
        return false;
    }

    public boolean isEmpty(){
        return customerList.isEmpty();
    }
//hearder
    private void template(){
        System.out.println("\n-----------------------------------");
        System.out.println("\t\t\tCustomer List");
        System.out.println("-----------------------------------");
        if (isEmpty()) {
            System.out.println("No customer yet.");
            System.out.println("-----------------------------------\n");
        }
    }

    public void simpleCustomerList(){
        template();
        System.out.println("ID\t\tName");
        for (Customer customer : customerList) {
            System.out.println(customer.getCustomerID() + "\t\t" + customer.getName());
        }
        System.out.println("-----------------------------------\n");
    }

    public void viewCustomerList(){
        template();
        System.out.printf("%-10s%-20s%-15s%-15s%-15s\n", "ID", "Name", "Phone Number", "Role", "Points");
        for (Customer customer : customerList) {
            System.out.printf("%-10s%-20s%-15s%-15s%-15s\n",
                    customer.getCustomerID(),
                    customer.getName(),
                    customer.getPhoneNumber(),
                    customer.getRole(),
                    customer.getCurrentPoints()
            );
        }
        System.out.println("-----------------------------------\n");
    }
}
