/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignmentsource;

/**
 *
 * @author Tan Jee Schuan
 */
public class Cashier extends User{
    
    public Cashier(String[] data){
        super(data);
    }
    
    @Override
    public void mainMenu() {
        System.out.print("Cashier\n1. New Sales\n2. View Items\n3. Logout");
    }
    
}
