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
        
        int menuSel = 0;
        do{
            System.out.print("\nManagement\n\n1. Manage Transactions\n2. Manage Customers\n3. Manage Inventory\n4. Logout\n\n");
            switch(menuSel = sc.nextInt())
            {
                case 1:
                    sPOS.viewTransactionList();
                    break;

                case 2:
                    break;

                case 3:
                    inventoryManagement();
                    break;
                    
                case 4:
                    return true;
            }  
        }while(menuSel != 4);
        
        return false;
    }
    
    public boolean inventoryManagement(){
        Scanner sc = new Scanner(System.in);
        
        StationaryPOS sPOS = StationaryPOS.getInstance();
        
        int sel = 0;
        do{
            System.out.print("\nInventory Management\n\n1. Add Item\n2. Modify Item\n3. Remove Item\n4. Display All Items\n5. Exit\n\n");
            switch(sel = sc.nextInt())
            {
                case 1:
                    sPOS.addItem();
                    break;

                case 2:
                    Item i = ((Item)Tools.objectSelection(sPOS.inv.getItemList()));
                    StationaryPOS.modifyItem(i);
                    break;

                case 3:
                    sPOS.inv.removeItem((Item)Tools.objectSelection(sPOS.inv.getItemList()));
                    break;
                    
                case 4:
                    sPOS.viewItemsList();
                    break;
                    
                case 5:
                    break;
            }  
        }while(sel != 5);
        
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
}
