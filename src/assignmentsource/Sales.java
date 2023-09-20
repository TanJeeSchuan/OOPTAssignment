package assignmentsource;

import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit; 



public class Sales implements Selectable{
    
    public final static String[] FILE_HEADER = {"saleID,timeOfSale,customerID"};
    public final static String STRING_FORMAT = "%-5d%-20s";
    public final static String FORMAT_HEADER = String.format("%-5s%-20s", "ID", "DateTime of Sale");
    
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
    public Sales(Customer customer, ArrayList<SoldItem> soldItems, int installmentTimes){
        this.saleID = newSaleID();
        this.dateTimeOfSale = LocalDateTime.now().truncatedTo( ChronoUnit.SECONDS).toString();
        
        this.customer = customer;
        this.soldItems = soldItems;
        
        for (SoldItem i : soldItems){
            i.writeToFile();
        }
        
        FileHandler.writeFile(FileHandler.SALES_DB, this.toCSV());
        
        //for every sale, must have transaction
        transaction = new Transaction(this.saleID, installmentTimes, calculateTotal());
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

    
    public double calculateTotal() {
        double total = 0;
        if (customer != null)
        {
            if ("wholesaler".equals(customer.getRole())){
                for(SoldItem sI: soldItems){
                    total += sI.getBulkSubTotal();
                }
                total += Wholesaler.getDeliveryFee();
            }
            
            else {
                for(SoldItem sI: soldItems){
                    total += sI.getSubTotal();
                }
            }
        }
        
        return total;
    }
    
    public double calculatePoints(){
        double points = 0;
        if (customer != null){
            if (!"wholesaler".equals(customer.getRole())){
                points = calculateTotal() / 10;
//                ((Retailer)customer).;
            }
            
            return 0;
        }
        
        return 0;
    }
    
    public static int newSaleID(){
        return CSVFile.getLastRowID(FileHandler.SALES_DB) + 1;
    }

    public String toCSV(){
        if(customer != null)
            return String.valueOf(saleID) + "," + dateTimeOfSale + "," + String.valueOf(customer.getCustomerID());
        else
            return String.valueOf(saleID) + "," + dateTimeOfSale + "," + "0";
    }
    
    public String expandAllDetail(){
        String b = "";
        for(SoldItem s: soldItems){
            String a = s.getSoldItem().toFormattedString() + " " + s.getQuantity() + " " + s.getSubTotal() +"\n" + b;
            b = a;
        }
        
        return FORMAT_HEADER + "Total" + "\n" 
                + toFormattedString() + "\n"
                + "--------------------------------------------------------------\n"
                + transaction.toFormattedString() + "\n" 
                + "--------------------------------------------------------------\n"
                + b;
    }
    
    @Override
    public String toFormattedString() {
        return String.format(STRING_FORMAT + "%.2f", saleID, dateTimeOfSale, calculateTotal());
    }
    
    @Override
    public String[] getFILEHEADER() {
        return FILE_HEADER;
    }
    @Override
    public String getSTRINGFORMAT() {
        return STRING_FORMAT;
    }
    @Override
    public String getFORMATHEADER() {
        return FORMAT_HEADER;
    }
}
