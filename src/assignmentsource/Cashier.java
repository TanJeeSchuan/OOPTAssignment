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
        
        int menuSel = 0;
        do{
            System.out.print("\nCashier\n1. New Sales\n2. New Customer\n3. Logout\n\n");
            switch(sc.nextInt())
            {
                case 1:
                    //create a sale then make payment
                    sPOS.salesPayment(sPOS.addSales().getTransaction());
                    break;

                case 2:
                    sPOS.addCustomer();
                    break;

                case 3:
                    return true;
            }
        }while(menuSel != 3);
        
        return false;
    }
    
    @Override
    public String toFormattedString(){
        return String.format(STRING_FORMAT,"Cashier",username, password, name, birthDate.toString(), phoneNumber);
    }
}
