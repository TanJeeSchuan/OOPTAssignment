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
    public void mainMenu() {
        Scanner sc = new Scanner(System.in);
        
        StationaryPOS sPOS = StationaryPOS.getInstance();
        
        System.out.print("Cashier\n1. New Sales\n2. View Items\n3. Logout\n\n");
        switch(sc.nextInt())
        {
            case 1:
                salesCreator();
                break;
                
            case 2:
                System.out.println(sPOS.cust.toString());
                break;
                
            case 3:
                break;
        }
    }
    
    public void salesCreator() {
        StationaryPOS sPOS = StationaryPOS.getInstance();
        Scanner sc = new Scanner(System.in);
        
        Item soldItem = null;
        int soldQty = 0;
        
        ArrayList<SoldItem> soldItemsList = new ArrayList<>();
        
        while(true){            
            soldItem = ((Item)Tools.objectSelection(sPOS.inv.getItemList()));  //get Selectable object. Cast Selectable to Item class
            
            if(soldItem == null)
                break;
            
            do{
                System.out.printf("\nEnter sold item quantity (< %d) : ", soldItem.getQuantity());
                soldQty = sc.nextInt();
            }while(soldQty > soldItem.getQuantity());
            
            soldItemsList.add(new SoldItem(Sales.newSaleID() ,soldItem, soldQty));
        }
        
        System.out.println(soldItemsList);
    }
    
    @Override
    public String toFormattedString(){
        return String.format(STRING_FORMAT,"Cashier",username, password, name, birthDate.toString(), phoneNumber);
    }
}
