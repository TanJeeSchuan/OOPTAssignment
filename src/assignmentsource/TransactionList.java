package assignmentsource;


import java.util.ArrayList;
import java.util.List;

public class TransactionList {

    private List<Transaction> transactionList;

    public TransactionList(){
        this.transactionList = Tools.initTransactions();
    }

    public Transaction getTransaction(int transactionID){
        for(Transaction transaction : transactionList){
            if(transaction.getTransactionID() == transactionID){
                return transaction;
            }
        }
        return null;
    }

    public void addTransaction(Transaction transaction){
        transactionList.add(transaction);
    }

    public void removeTransaction(Transaction transaction){
        transactionList.remove(transaction);
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    public boolean isIDExist(int transactionID){
        for(Transaction transaction : transactionList){
            if(transaction.getTransactionID() == transactionID){
                return true;
            }
        }
        return false;
    }

    public boolean isEmpty(){
        return transactionList.isEmpty();
    }

    private void template(){
        System.out.println("\n-----------------------------------");
        System.out.println("\t\tTransaction List");
        System.out.println("-----------------------------------");
        if(isEmpty()){
            System.out.println("No transaction found");
            System.out.println("-----------------------------------\n");
        }
    }

    public void viewTransactionList(){
        template();
        for(Transaction transaction : transactionList){
            System.out.printf("%-20s%-20s\n", "Transaction ID:", transaction.getTransactionID());
            System.out.printf("%-20s%-20s\n", "Datetime:", transaction.getSales().getTimeOfSale());
            System.out.printf("%-20s%-20s\n", "Customer Name:", transaction.getCustomer().getName());
            System.out.printf("%-20s%-20s\n", "Total Amount:", transaction.getTotalAmount());
            if (transaction.getInstallmentTimes() == 1) {
                System.out.printf("%-20s%-20s\n", "Payment Type:", "One Time Payment");
            } else {
                System.out.printf("%-20s%-20s\n", "Payment Type:", "Installment");
                System.out.printf("%-20s%-20s\n", "Installment Period:", transaction.getInstallmentTimes()+" months");
                System.out.printf("%-20s%-20s\n", "Time Left:", transaction.getTimesLeft());
                System.out.printf("%-20s%-20s\n", "Balance Left:", transaction.getBalanceLeft());
            }
            System.out.println("-----------------------------------\n");
        }
    }

    private void installmentTemplate(List<Transaction> list) {
        System.out.println("\n-----------------------------------");
        System.out.println("\t\tActive Installment");
        System.out.println("-----------------------------------");
        if (list.isEmpty()) {
            System.out.println("No installment found");
            System.out.println("-----------------------------------\n");
        }
    }

    public void displayActiveInstallment(){
        //only select the transaction that is not fully paid
        List<Transaction> activeInstallment = selectActiveInstallment();
        installmentTemplate(activeInstallment);
        for (Transaction transaction : activeInstallment) {
            System.out.printf("%-20s%-20s\n", "Transaction ID:", transaction.getTransactionID());
            System.out.printf("%-20s%-20s\n", "Datetime:", transaction.getSales().getTimeOfSale());
            System.out.printf("%-20s%-20s\n", "Customer Name:", transaction.getCustomer().getName());
            System.out.printf("%-20s%-20s\n", "Total Amount:", transaction.getTotalAmount());
            System.out.printf("%-20s%-20s\n", "Installment Period:", transaction.getInstallmentTimes()+" months");
            System.out.printf("%-20s%-20s\n", "Time Left:", transaction.getTimesLeft());
            System.out.printf("%-20s%-20s\n", "Balance Left:", transaction.getBalanceLeft() + "\n");
            System.out.println("-----------------------------------\n");
        }
    }

    public void simpleActiveInstallmentList(){
        List<Transaction> activeInstallment = selectActiveInstallment();
        installmentTemplate(activeInstallment);
        System.out.printf("%-16s%-20s%-12s%12s\n", "Transaction ID", "Installment Period", "Time Left", "Balance Left");
        for (Transaction transaction : activeInstallment) {
            System.out.printf("%-16s%-20s%-12s%12s\n",
                    transaction.getTransactionID(),
                    transaction.getInstallmentTimes()+" months",
                    transaction.getTimesLeft(),
                    transaction.getBalanceLeft()
            );
        }
    }

    private List<Transaction> selectActiveInstallment(){
        List<Transaction> activeInstallment = new ArrayList<>();
        for(Transaction transaction : transactionList){
            if(transaction.getInstallmentTimes() > 1 && transaction.getBalanceLeft() > 0){
                activeInstallment.add(transaction);
            }
        }
        return activeInstallment;
    }

}
