package assignmentsource;


public class Transaction implements Selectable{
    
    public final static String[] FILE_HEADER = {"transactionID,installmentTimes,balanceLeft,timesLeft,totalAmount"};
    public final static String STRING_FORMAT = "%-5d%-12d%-20.2f%-15d%-15f";
    public final static String FORMAT_HEADER = String.format("%-5s%-10s%-15s%-15s%-15s", "ID", "installmentTimes", "Balance", "timesLeft", "Amount");
    
    private int transactionID;
    private int installmentTimes;
    private double balanceLeft;
    private int timesLeft;
    private double totalAmount;

    //reading from file
    public Transaction(String[] transactionString) {
        this.transactionID = Integer.parseInt(transactionString[0]);
        this.installmentTimes = Integer.parseInt(transactionString[1]);
        this.balanceLeft = Double.parseDouble(transactionString[2]);
        this.timesLeft = Integer.parseInt(transactionString[3]);
        this.totalAmount = Double.parseDouble(transactionString[4]);
    }

//one to one transaction and sales, transactionID will be equal to customerID, new transaction
    public Transaction(int custID, int installmentTimes, double totalAmount){
        this.transactionID = custID;
        
        this.installmentTimes = installmentTimes;
        this.timesLeft = installmentTimes;
        
        this.totalAmount = totalAmount;
        this.balanceLeft = totalAmount;
        
        FileHandler.writeFile(FileHandler.TRANSACTION_DB, this.toCSV());
    }

    public int getTransactionID() {
        return transactionID;
    }

    public double getBalanceLeft() {
        return balanceLeft;
    }

    public int getInstallmentTimes() {
        return installmentTimes;
    }

    public int getTimesLeft() {
        return timesLeft;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
    
    public boolean paymentFinished(){
        return balanceLeft <= 0;
    }
    
    public boolean isInstallment(){
        if(installmentTimes == 0)
            return false;
        else
            return true;
    }

    public double getMonthlyPayment(){
        if (installmentTimes != 0)
            return Math.round(this.totalAmount / this.installmentTimes * 100.0) / 100.0;
        else
            return 0;
    }
    
    public void completePayment(){
        this.timesLeft = 0;
        this.balanceLeft = 0;
        updateFile();
    }

    public void payMonthly(){
        if (timesLeft > 0){
            this.timesLeft--;
            this.balanceLeft = Math.round((this.balanceLeft - this.getMonthlyPayment()) * 100.0) / 100.0;
            
            if (balanceLeft > 0)
                this.balanceLeft = 0;
            updateFile();
        }
    }
    
    //return balance
    public double pay(double paymentAmount){
        this.balanceLeft -= paymentAmount;
        if (balanceLeft <= 0){
            double balance = -balanceLeft;
            completePayment();
            return balance;
        }
        else
            return 0;
    }
        
    public String toCSV() {
        return this.transactionID + "," + this.installmentTimes + "," + this.balanceLeft + ","  + this.timesLeft + "," + this.totalAmount;
    }

    @Override
    public String toString(){
        return this.transactionID + "," + this.installmentTimes + "," + this.balanceLeft + ","  + this.timesLeft + "," + this.totalAmount;
    }

    private void updateFile(){
        CSVFile.updateDataByRow(FileHandler.TRANSACTION_DB, transactionID, toCSV().split(","));
    }

    private void generateLog(){
//        Tools.generatePaymentLog(
//                this.customer.getName(),
//                getMonthlyPayment(),
//                this.installmentTimes == 1 ? "One-time Payment" : "Installment"
//        );
        ;
    }

    @Override
    public String toFormattedString() {
        return String.format(STRING_FORMAT, transactionID, installmentTimes, balanceLeft, timesLeft, totalAmount);
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
