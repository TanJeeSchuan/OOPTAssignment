/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignmentsource;

/**
 *
 * @author user
 */
public class Transaction {
    private int transactionID;
    private int customerID;
    private Customer customer;
    private double balanceLeft;
    private int installmentTimes;
    private int timesLeft;
    private double totalAmount;
    //private Sales sales;

    //used to create new transaction
    //transactionID,customerID,installmentTimes,balanceLeft,timesLeft,totalAmount
    public Transaction(int customerID, double balanceLeft, int installmentTimes, int timesLeft, double totalAmount) {
        this.transactionID = Tools.getNewID(FileHandler.TRANSACTION_DB);
        this.customerID = customerID;
        this.customer = StaticContainer.customerList.getCustomer(customerID); //get customer object from customer list
        this.balanceLeft = balanceLeft;
        this.installmentTimes = installmentTimes;
        this.timesLeft = timesLeft;
        this.totalAmount = totalAmount;
       // this.sales = StaticContainer.currentSale;
        FileHandler.writeFile(FileHandler.TRANSACTION_DB, this.toString());
    }

    public Transaction(int transactionID, int customerID, double balanceLeft, int installmentTimes, int timesLeft, double totalAmount) {
        this.transactionID = transactionID;
        this.customerID = customerID;
        this.customer = StaticContainer.customerList.getCustomer(customerID); //get customer object from customer list
        this.balanceLeft = balanceLeft;
        this.installmentTimes = installmentTimes;
        this.timesLeft = timesLeft;
        this.totalAmount = totalAmount;
       
    }
    
    //array constructor

    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public Customer getCustomer(){
        return this.customer;
    }

    public void setCustomer(Customer customer){
        this.customer = customer;
    }

    public double getBalanceLeft() {
        return balanceLeft;
    }

    public void setBalanceLeft(double balanceLeft) {
        this.balanceLeft = balanceLeft;
    }

    public int getInstallmentTimes() {
        return installmentTimes;
    }

    public void setInstallmentTimes(int installmentTimes) {
        this.installmentTimes = installmentTimes;
    }

    public int getTimesLeft() {
        return timesLeft;
    }

    public void setTimesLeft(int timesLeft) {
        this.timesLeft = timesLeft;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

 //insltamment

    public double getMonthlyPayment(){
        return Math.round(this.totalAmount / this.installmentTimes * 100.0) / 100.0;
    }

    public void payMonthly(){
        this.timesLeft--;
        this.balanceLeft = Math.round((this.balanceLeft - this.getMonthlyPayment()) * 100.0) / 100.0;
        updateTransaction();
    }
    
    //public void payMonthly(double amountPaid);

    public String toString(){
        return this.transactionID + "," + this.customerID + "," + this.installmentTimes + "," + this.balanceLeft + ","  + this.timesLeft + "," + this.totalAmount;
    }

    private void updateTransaction(){
        FileHandler.updateDataByID(
                FileHandler.TRANSACTION_DB,
                String.valueOf(this.transactionID),
                "timesLeft",
                String.valueOf(this.timesLeft)
        );
        FileHandler.updateDataByID(
                FileHandler.TRANSACTION_DB,
                String.valueOf(this.transactionID),
                "balanceLeft",
                String.valueOf(this.balanceLeft)
        );
    }


}

