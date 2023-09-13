/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignmentsource;

/**
 *
 * @author user
 */
public class Retailer extends Customer{

    public Retailer(int customerID) {
        super(customerID);
    }

    public Retailer(String name, String phoneNumber, String role) {
        super(name, phoneNumber, role);
    }

    public Retailer(int customerID, String name, String phoneNumber, int currentPoints, String role) {
        super(customerID, name, phoneNumber, currentPoints, role);
    }

    public static double getDiscount(double price){
        return Math.floor(price / 200);  //each 200 discount 1
    }
}

