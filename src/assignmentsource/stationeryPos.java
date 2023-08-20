/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package assignmentsource;
import java.util.Scanner;

/**
 *
 * @author Tan Jee Schuan
 */


//CAN YOU SEE ME? IF FROM GITHUB YOU CAN SEE THIS

//GITHUB TEST

import java.util.List;
import java.util.ArrayList;

public class stationeryPos{

    Inventory inv;
    Sales sales;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Inventory inv = new Inventory();
        Sales sales = new Sales();
        
        inv.addItem(1234, "TESTNAME", 23, 2.50);
        inv.addItem(1532,"ABC",59,59.99);
        inv.addItem(3289522, "Candy", 150, 0.99);
        
        List<Item> soldItemsList = new ArrayList<Item>();
        soldItemsList.add(inv.getItem(1234));
        soldItemsList.add(inv.getItem(1234));
        soldItemsList.add(inv.getItem(1234));
        soldItemsList.add(inv.getItem(3289522));
        
        sales.addTransaction(soldItemsList);
        
        soldItemsList.add(inv.getItem(3289522));
        sales.addTransaction(soldItemsList);
        
        System.out.println(sales.getTransaction(2).total);
        
        PhysicalSale ps = new PhysicalSale();
        ps.
    }
    
}
