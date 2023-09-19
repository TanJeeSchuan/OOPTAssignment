/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignmentsource;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Tan Jee Schuan
 */
public class Cashier extends User{
    
    public Cashier(String[] data){
        super(data);
    }
    
    @Override
    public boolean mainMenu() {
        Scanner sc = new Scanner(System.in);
        
        StationaryPOS sPOS = StationaryPOS.getInstance();
        
        System.out.print("Cashier\n1. New Sales\n2. New Customer\n3. Logout\n\n");
        switch(sc.nextInt())
        {
            case 1:
                sPOS.addSales();
                break;
                
            case 2:
                System.out.println(sPOS.inv);
                break;
                
            case 3:
                return true;
        }
        
        return false;
    }
    
    @Override
    public String toFormattedString(){
        return String.format(STRING_FORMAT,"Cashier",username, password, name, birthDate.toString(), phoneNumber);
    }
}
