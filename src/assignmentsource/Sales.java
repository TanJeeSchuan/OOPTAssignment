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
public class Sales {
    List<transaction> transactionList = new ArrayList<transaction>();
    
    Sales()
    {
        
    }
    Sales(List<transaction> transactionList)
    {
        this.transactionList = transactionList;
    }
    
    public transaction getTransaction(int transactionID)
    {
        for (transaction t : transactionList)
        {
            if (transactionID == t.transactionID)
                return t;
        }
        
        return null;
    }
    
    public void addTransaction(List<Item> soldItems)    //create transaction based on list of sold items
    {
        transactionList.add(new transaction(soldItems));
    }
    
    public void deleteTransaction(int transactionID)
    {
        for (transaction t : transactionList)
        {
            if (transactionID == t.transactionID)
                transactionList.remove(t);
        }
    }
    
}
