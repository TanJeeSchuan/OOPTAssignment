package assignmentsource;

import java.util.ArrayList;

public class Customer implements Selectable{

    public final static String[] FILE_HEADER = {"customerID,customerName,phoneNumber,currentPoints,role"};
    public final static String STRING_FORMAT = "%-5d%-40s%20s%15d%15s";
    public final static String FORMAT_HEADER = String.format("%-5s%-40s%20s%15s%15s", "ID", "Name", "phoneNumber", "currentPoints", "role");
    
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
        FileHandler.writeFile(FileHandler.CUSTOMER_DB, this.toCSV());
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        updateCustomer();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        updateCustomer();
    }

    public int getCurrentPoints() {
        return currentPoints;
    }

    public void setCurrentPoints(int currentPoints) {
        this.currentPoints = currentPoints;
        updateCustomer();
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role=role;
        updateCustomer();
    }

    //update point
    public void modifyCurrentPoints(int points) {
        this.currentPoints += points;
        updateCustomer();
    }

//display
//    public void displayCustomerInfo(){
//        System.out.println("\n-----------------------------------");
//        System.out.printf("Customer ID: %d\n", this.customerID);
//        System.out.printf("Name: %s\n", this.name);
//        System.out.printf("Phone Number: %s\n", this.phoneNumber);
//        System.out.printf("Current Points: %d\n", this.currentPoints);
//        System.out.printf("Role: %s\n", this.role);
//        System.out.println("-----------------------------------\n");
//    }
    
    public void updateCustomer(){
        CSVFile.updateDataByRow(FileHandler.CUSTOMER_DB, customerID, this.toCSV().split(","));
    }

    @Override
    public String toString() {
        return this.customerID + "," + this.name + "," + this.phoneNumber + "," + this.currentPoints + "," + this.role;
    }
    
    public String toCSV() {
        return this.customerID + "," + this.name + "," + this.phoneNumber + "," + this.currentPoints + "," + this.role;
    }
    //testing

    @Override
    public String toFormattedString() {
        return String.format("%-5d%-40s%20s%15d%15s", customerID, name, phoneNumber, currentPoints, role);
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
