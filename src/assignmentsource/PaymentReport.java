//package assignmentsource;
//
//import java.util.HashMap;
//import java.util.List;
//
//public class PaymentReport extends Report{
//
//    //count the total received payment number (installment or one time payment)
//    //count the total amount of payment received (installment or one time payment)
//    //count the total amount of haven't fully collected payment (installment)
//    private List<Transaction> transactionList;
//    private int installmentCount;
//    private int fullPaymentCount;
//    private HashMap<String, Double> paymentReceived;
//    private double amountHaventCollected;
//
//    public PaymentReport() {
//        super();
//        initialize();
//    }
//
//    @Override
//    public void initialize() {
//        this.transactionList = StaticContainer.transactionList.getTransactionList();
//        paymentReceived = new HashMap<>();
//        paymentReceived.put("Installment", 0.0);
//        paymentReceived.put("One Time Payment", 0.0);
//    }
//
//    @Override
//    public void print() {
//        template();
//        System.out.printf("%-40s%-15s\n","Total number of installment:", installmentCount);
//        System.out.println("-------------------------------------------");
//        System.out.printf("%-40s%-15s\n","Total number of one time payment:", fullPaymentCount);
//        System.out.println("-------------------------------------------");
//        System.out.printf("%-40s%-15s\n","Installment received total:", "RM" + paymentReceived.get("Installment"));
//        System.out.println("-------------------------------------------");
//        System.out.printf("%-40s%-15s\n","One time payment received total:", "RM" + paymentReceived.get("One Time Payment"));
//        System.out.println("-------------------------------------------");
//        System.out.printf("%-40s%-15s\n","Not fully collected payment total:", "RM" + amountHaventCollected);
//        System.out.println("-------------------------------------------\n");
//    }
//
//    @Override
//    public void template() {
//        System.out.println("-------------------------------------------");
//        System.out.println("\t\t\tPayment Report");
//        System.out.println("-------------------------------------------");
//    }
//
//    @Override
//    public void analyze() {
//        for (Transaction transaction : transactionList) {
//            if (transaction.getInstallmentTimes() == 1) {
//                fullPaymentCount++;
//                paymentReceived.put("One Time Payment", paymentReceived.get("One Time Payment") + transaction.getTotalAmount());
//            } else {
//                installmentCount++;
//                amountHaventCollected += transaction.getBalanceLeft();
//                paymentReceived.put("Installment", paymentReceived.get("Installment") + transaction.getTotalAmount() - transaction.getBalanceLeft());
//            }
//        }
//    }
//}
