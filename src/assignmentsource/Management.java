/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignmentsource;

import java.util.Scanner;

/**
 *
 * @author Tan Jee Schuan
 */
public class Management extends User{
    
    public Management(String[] data){
        super(data);
    }
    
    @Override
    public boolean mainMenu() {
        Scanner sc = new Scanner(System.in);
        
        StationaryPOS sPOS = StationaryPOS.getInstance();
        
        System.out.print("Management\n\n1. Manage Transactions\n2. Manage Customers\n3. Logout\n\n");
        switch(sc.nextInt())
        {
            case 1:
                viewSales();
                break;
                
            case 2:
                sPOS.viewTransactionList();
                break;
                
            case 3:
                return true;
        }    
        
        return false;
    }
    
    public String toFormattedString(){
        return String.format(STRING_FORMAT,"Management",username, password, name, birthDate.toString(), phoneNumber);
    }

    public static void viewSales() {
        StationaryPOS sPOS = StationaryPOS.getInstance();

        for(Sales s: sPOS.sales)
            System.out.println(s.toFormattedString()+ '\n');
    }
    
    public static void viewAllTransactions() {
        StationaryPOS sPOS = StationaryPOS.getInstance();

        for(Sales s: sPOS.sales)
            System.out.println(s.getTransaction().toFormattedString()+ '\n');
    }
}
