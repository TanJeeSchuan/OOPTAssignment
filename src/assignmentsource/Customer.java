/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignmentsource;

/**
 *
 * @author Cha Wan Xun
 */
import java.util.ArrayList;

public class Customer {
    private int customerID;
    private String name;
    private String phoneNumber;
    private int currentPoints;
    //no have role !
    private String role;
    
//array list customerID,customerName,phoneNumber,currentPoints,role
    //filehandle， try catch
    public Customer(int customerID) {
        ArrayList<String> customerInfo = FileHandler.getRowByMainID(FileHandler.CUSTOMER_DB, String.valueOf(customerID));
        this.customerID = customerID;
        this.name = customerInfo.get(1);//get as 1
        this.phoneNumber = customerInfo.get(2);
        this.currentPoints = Integer.parseInt(customerInfo.get(3));
        this.role = customerInfo.get(4);
    }
//create or registration for new customer
    public Customer(String name, String phoneNumber, String role) {
        //tool class to create new id
        this.customerID = Tools.getNewID(FileHandler.CUSTOMER_DB);
        this.name = name;
        this.phoneNumber = phoneNumber;
        //initial point set as zero
        this.currentPoints = 0;
        this.role = role;
        //write into file
        FileHandler.writeFile(FileHandler.CUSTOMER_DB, this.toString());
    }
    
    //constructor to create object from array

    public Customer(int customerID, String name, String phoneNumber, int currentPoints, String role) {
        this.customerID = customerID;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.currentPoints = currentPoints;
        this.role = role;
    }
    
//getter setter part
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
//role may cancel !
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role=role;
    }

//update current points
    public void updateCurrentPoints(int points) {
        this.currentPoints += points;

        FileHandler.updateDataByID(
                FileHandler.CUSTOMER_DB,
                String.valueOf(this.customerID),
                "currentPoints",
                String.valueOf(this.currentPoints)
        );
    }
//display list
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
}

