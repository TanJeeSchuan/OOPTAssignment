package assignmentsource;

import java.util.ArrayList;
import java.time.LocalDateTime;

public class Sales{

    private int saleID;
    private String dateTimeOfSale;
    
    private Customer customer;
    private Transaction transaction;
    private ArrayList<SoldItem> soldItems;
    
    public Sales(int saleID, String timeOfSale, Customer customer, ArrayList<SoldItem> soldItems) {
        this.saleID = saleID;
        this.dateTimeOfSale = timeOfSale;
        this.customer = customer;
        this.soldItems = soldItems;
    }
    
    //for initialse from file
    public Sales(String salesString[], ArrayList<Customer> custList, ArrayList<Transaction> transactionList, ArrayList<SoldItem> soldItemsList){
        this.saleID = Integer.parseInt(salesString[0]);
        this.dateTimeOfSale = salesString[1];
        
        this.soldItems = new ArrayList();
        //get existing customer
        for (Customer c: custList){
            if (c.getCustomerID() == Integer.parseInt(salesString[2])){
                this.customer = c;
                break;
            }
        }
        
        for (Transaction t: transactionList){
            if (t.getTransactionID() == this.saleID){
                this.transaction = t;
                break;
            }
        }
        
        for (SoldItem sI: soldItemsList){
            if (sI.getSaleID() == this.saleID){
                this.soldItems.add(sI);
            }
        }
    }
    
    //for new sales created by user?
    public Sales(Customer customer, ArrayList<SoldItem> soldItems){
        this.saleID = CSVFile.getLastRowID(FileHandler.SALES_DB) + 1;
        this.dateTimeOfSale = LocalDateTime.now().toString();
        
        this.customer = customer;
        this.soldItems = soldItems;    
    }

    public int getSaleID() {
        return saleID;
    }

    public String getDateTimeOfSale() {
        return dateTimeOfSale;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public ArrayList<SoldItem> getSoldItems() {
        return soldItems;
    }
    

}
