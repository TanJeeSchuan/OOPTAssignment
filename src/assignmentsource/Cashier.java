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
                    do{
                        String sel = "";
                        do{
                            sPOS.addCustomer();
                            System.out.print("\n\nContinue adding customer?(y/n):");
                            sel = sc.next();
                        }while(!("y".equals(sel)) && !("n".equals(sel)));

                        if("n".equals(sel))
                            break;
                    }while(true);
                    break;
                    
            case 3:
                    ArrayList<Transaction> tList = sPOS.getTransactionList();

                    if(!tList.isEmpty()){
                        ArrayList<Transaction> uncompleteTList = sPOS.getTransactionType(tList, false);

                        if(!uncompleteTList.isEmpty()){
                            Transaction t = (Transaction)Tools.objectSelection(uncompleteTList);  //get uncomplete transactions

                            if(t != null){
                                sPOS.salesPayment(t);
                                StationaryPOS.viewTransaction(t);
                            }
                            else
                                System.out.println("No Transaction Selected");
                        }
                        else
                            System.out.println("No Uncomplete Transactions");
                    }
                    else
                        System.out.println("No Transactions");
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
