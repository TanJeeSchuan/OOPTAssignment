/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignmentsource;

/**
 *
 * @author user
 */

//noo
public class Wholesaler extends Customer{

    private final double pointsPer = 0.5;

    public Wholesaler(int customerID) {
        super(customerID);
    }

    public Wholesaler(String name, String phoneNumber, String role) {
        super(name, phoneNumber, role);
    }

    public Wholesaler(int customerID, String name, String phoneNumber, int currentPoints, String role) {
        super(customerID, name, phoneNumber, currentPoints, role);
    }

    public static double getDeliveryFee(){
        return 150;
    }
}

