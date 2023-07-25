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
public class stationeryPos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Inventory inv = new Inventory();
        inv.addItem(1234, "TESTNAME", 23, 2.50);
        
        inv.getItem(1234).modifyQuantity(-4);
        
        System.out.println(inv.getItem(1234).quantity);
    }
    
}
