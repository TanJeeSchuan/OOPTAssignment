/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignmentsource;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */

public class Tools {
  
    public static int getNewID(String db){
        return FileHandler.getLastRowID(db) + 1;
    }
    
     public static List<Customer> initCustomers() {
        ArrayList<String[]> customers = FileHandler.readFileToArray(FileHandler.CUSTOMER_DB);
        // remove the header
        customers.remove(0); 
        ArrayList<Customer> customerList = new ArrayList<>();
        for (String[] customer : customers) {
            customerList.add(new Customer(
                    Integer.parseInt(customer[0]),
                    customer[1],
                    customer[2],
                    Integer.parseInt(customer[3]),
                    customer[4])
            );
        }
        return customerList;
        
        //for (cEle : customers) {
        //    customerList.add(new Customer(cEle);
        //    );
    }
     
      
      
    
    public static String getCurrentDateTime() {
        return java.time.LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
    
    public static List<Integer> stringToIntList(String str) {
        List<Integer> intList = new ArrayList<>();
        String[] strArr = str.split(":");
        for (String s : strArr) {
            intList.add(Integer.parseInt(s));
        }
        return intList;
    }
    
    
     public static List<Transaction> initTransactions() {
        ArrayList<String[]> transactions = FileHandler.readFileToArray(FileHandler.TRANSACTION_DB);
        transactions.remove(0);  // remove the header
        ArrayList<Transaction> transactionList = new ArrayList<>();
        for (String[] transaction : transactions) {
            transactionList.add(new Transaction(
                    Integer.parseInt(transaction[0]),
                    Integer.parseInt(transaction[1]),
                    Double.parseDouble(transaction[3]),
                    Integer.parseInt(transaction[2]),
                    Integer.parseInt(transaction[4]),
                    Double.parseDouble(transaction[5])
            ));
        }
        return transactionList;
    }

}
    