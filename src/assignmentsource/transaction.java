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
    static int globalTransactionID = 0;
    
    int transactionID;
    List<Item> soldItems = new ArrayList();
    double total;
    
    //constructor
    transaction(List<Item> soldItemsList)
    {
        transactionID = globalTransactionID;
        globalTransactionID++;
        
        for (Item i : soldItems)
        {
            total += i.quantity * i.price;
        }
    }
}
