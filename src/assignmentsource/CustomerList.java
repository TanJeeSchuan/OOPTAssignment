/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignmentsource;

/**
 *
 * @author user
 */

//Different customer list

import java.util.List;

public class CustomerList {
    private List<Customer> customerList;
    
    //to store customerlist
    public CustomerList(){
        this.customerList = Tools.initCustomers();
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
//
    public List<Customer> getCustomerList() {
        return customerList;
    }
    
    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }
//
    //to prevent same customer id 
    public boolean isIDExist(int customerID){
        for(Customer customer : customerList){
            if(customer.getCustomerID() == customerID){
                return true;
            }
        }
        return false;
    }
    //check the file empty 
    public boolean isEmpty(){
        return customerList.isEmpty();
    }

    private void template(){
        System.out.println("\n-----------------------------------");
        System.out.println("\t\t\tCustomer List");
        System.out.println("-----------------------------------");
        //if the file is empty
        if (isEmpty()) {
            System.out.println("No customer yet.");
            System.out.println("-----------------------------------\n");
        }
    }
    //use for simple confirm the customer list
    public void simpleCustomerList(){
        template();
        System.out.println("ID\t\tName");
        for (Customer customer : customerList) {
            System.out.println(customer.getCustomerID() + "\t\t" + customer.getName());
        }
        System.out.println("-----------------------------------\n");
    }

    //use for vewing the all customer list 
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

