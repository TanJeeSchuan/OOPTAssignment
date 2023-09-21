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
    
    public Cashier (String username, String password, String name, String phoneNumber){
        super(username, password, name, phoneNumber);
    }
    
    @Override
    public boolean mainMenu() {
        Scanner sc = new Scanner(System.in);
        
        StationaryPOS sPOS = StationaryPOS.getInstance();
        
        int menuSel = 0;
        do{
            System.out.print("\nCashier\n1. New Sales\n2. New Customer\n3. Pay Outstanding Transactions\n4. Logout\n\n");
            switch(sc.nextInt())
            {
                case 1:
                    //create a sale then make payment
                    Sales s = sPOS.addSales();
                    sPOS.salesPayment(s.getTransaction());
                    
                    //update quantity
                    sPOS.updateItemQuantity(s.getSoldItems());
                    
                    //add points
                    s.modifyCustomerPoints(s.calculatePoints());
                    break;

                case 2:
                    sPOS.addCustomer();
                    break;
                    
                case 3:
                    Transaction t = (Transaction)Tools.objectSelection(sPOS.getTransactionType(sPOS.getTransactionList(), false));
                    
                    if (t != null)
                        sPOS.salesPayment(t);
                    else
                        System.out.println("No Transaction Selected");
                    break;
                    
                case 4:
                    return true;
            }
        }while(menuSel != 4);
        
        return false;
    }
    
    @Override
    public String toFormattedString(){
        return String.format(STRING_FORMAT,"Cashier",username, password, name, phoneNumber);
    }
    
    public String toCSV(){
        return "Cashier," + super.toCSV();
    }
}
