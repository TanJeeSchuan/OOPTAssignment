package assignmentsource;

public interface ITransaction {
    int getTransactionID();
    void setTransactionID(int transactionID);
    int getCustomerID();
    void setCustomerID(int customerID);
    Customer getCustomer();
    void setCustomer(Customer customer);
    double getBalanceLeft();
    void setBalanceLeft(double balanceLeft);
    int getInstallmentTimes();
    void setInstallmentTimes(int installmentTimes);
    int getTimesLeft();
    void setTimesLeft(int timesLeft);
    double getTotalAmount();
    void setTotalAmount(double totalAmount);
    Sales getSales();
    void setSales(Sales sales);
    double getMonthlyPayment();
    void payMonthly();
    String toString();
}
