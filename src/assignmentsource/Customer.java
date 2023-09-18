package assignmentsource;

import java.util.ArrayList;

public class Customer {

    private int customerID;
    private String name;
    private String phoneNumber;
    private int currentPoints;
    private String role;
//role for wholesaler and retailer
//wholesaler no track point 
//retailer track point and use for discount
    
    public Customer(int customerID) {
        ArrayList<String> customerInfo = CSVFile.getRowByMainID(FileHandler.CUSTOMER_DB, String.valueOf(customerID));
        this.customerID = customerID;
        this.name = customerInfo.get(1);
        this.phoneNumber = customerInfo.get(2);
        this.currentPoints = Integer.parseInt(customerInfo.get(3));
        this.role = customerInfo.get(4);
    }

    //used to create new customer
    public Customer(String name, String phoneNumber, String role) {
        this.customerID = Tools.getNewID(FileHandler.CUSTOMER_DB);
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.currentPoints = 0;
        this.role = role;
        FileHandler.writeFile(FileHandler.CUSTOMER_DB, this.toString());
    }

    public Customer(String[] data) {
        this.customerID = Integer.parseInt(data[0]);
        this.name = data[1];
        this.phoneNumber = data[2];
        this.currentPoints = Integer.parseInt(data[3]);
        this.role = data[4];
    }

    public Customer(int customerID, String name, String phoneNumber, int currentPoints, String role) {
        this.customerID = customerID;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.currentPoints = currentPoints;
        this.role = role;
    }
//getter setter
    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getCurrentPoints() {
        return currentPoints;
    }

    public void setCurrentPoints(int currentPoints) {
        this.currentPoints = currentPoints;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role=role;
    }

    //update point
    public void updateCurrentPoints(int points) {
        this.currentPoints += points;

        CSVFile.updateDataByID(
                FileHandler.CUSTOMER_DB,
                String.valueOf(this.customerID),
                "currentPoints",
                String.valueOf(this.currentPoints)
        );
    }
//display
    public void displayCustomerInfo(){
        System.out.println("\n-----------------------------------");
        System.out.printf("Customer ID: %d\n", this.customerID);
        System.out.printf("Name: %s\n", this.name);
        System.out.printf("Phone Number: %s\n", this.phoneNumber);
        System.out.printf("Current Points: %d\n", this.currentPoints);
        System.out.printf("Role: %s\n", this.role);
        System.out.println("-----------------------------------\n");
    }

    public String toString() {
        return this.customerID + "," + this.name + "," + this.phoneNumber + "," + this.currentPoints + "," + this.role;
    }
    //testing
}
