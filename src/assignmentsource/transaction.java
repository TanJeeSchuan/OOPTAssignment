/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignmentsource;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Tan Jee Schuan
 */
public class transaction {
    static int globalTransactionID = 1;
    int transactionID;
    double total = 0;
    
    //constructor
    transaction(List<Item> soldItemsList)
    {
        transactionID = globalTransactionID;
        globalTransactionID++;
        
        for (Item i : soldItemsList)
        {
            total += i.quantity * i.price;
        }
    }
}
