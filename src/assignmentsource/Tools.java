package assignmentsource;


import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Tools {

    //init item
    public static ArrayList<Item> initItems() {
        ArrayList<String[]> items = FileHandler.readFileToArray(FileHandler.INVENTORY_DB);
        items.remove(0);  // remove the header
        ArrayList<Item> itemList = new ArrayList<>();
        for (String[] item : items) {
            itemList.add(new Item(Integer.parseInt(item[0]),
                    item[1], item[2],
                    Integer.parseInt(item[3]),
                    Double.parseDouble(item[4]),
                    Double.parseDouble(item[5]))
            );
        }
        return itemList;
    }

    public static String generateBarCode() {
        //random 10 digit number
        StringBuilder barcode = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            barcode.append((int) (Math.random() * 10));
        }
        return barcode.toString();
    }

    public static int getNewID(String db){
        return FileHandler.getLastRowID(db) + 1;
    }

    public static List<Integer> stringToIntList(String str) {
        List<Integer> intList = new ArrayList<>();
        String[] strArr = str.split(":");
        for (String s : strArr) {
            intList.add(Integer.parseInt(s));
        }
        return intList;
    }

    public static String intListToString(List<Integer> soldItemsID) {
        StringBuilder str = new StringBuilder();
        if (soldItemsID.size() > 0) {
            for (int i = 0; i < soldItemsID.size() - 1; i++) {
                str.append(soldItemsID.get(i)).append(":");
            }
            str.append(soldItemsID.get(soldItemsID.size() - 1));
        }
        return str.toString();
    }
//initcustomer
    public static List<Customer> initCustomers() {
        //read file become 2d array
        ArrayList<String[]> customers = FileHandler.readFileToArray(FileHandler.CUSTOMER_DB);
        customers.remove(0);  // remove the header
        //prepare a list
        ArrayList<Customer> customerList = new ArrayList<>();
        //make them become object
        for (String[] customer : customers) {
            if (customer[4].equals("retailer")) {
                customerList.add(new Retailer(
                        Integer.parseInt(customer[0]),
                        customer[1],
                        customer[2],
                        Integer.parseInt(customer[3]),
                        customer[4])
                );
            }
            else {
                customerList.add(new Wholesaler(
                        Integer.parseInt(customer[0]),
                        customer[1],
                        customer[2],
                        Integer.parseInt(customer[3]),
                        customer[4])
                );
            }
        }
        return customerList;
    }
//init user
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
  //init user
    public static ArrayList<Sales> initSales() {
        ArrayList<String[]> sales = FileHandler.readFileToArray(FileHandler.SALES_DB);
        sales.remove(0);  // remove the header
        ArrayList<Sales> salesList = new ArrayList<>();
        for (String[] sale : sales) {
            salesList.add(
                    new Sales(
                            Integer.parseInt(sale[0]),
                            sale[1],
                            Integer.parseInt(sale[2]),
                            Tools.stringToIntList(sale[3]),
                            Tools.stringToIntList(sale[4])
                    )
            );
        }
        return salesList;
    }

    public static Customer getCustomerByID(int id) {
        ArrayList<String> customerInfo = FileHandler.getRowByMainID(FileHandler.CUSTOMER_DB, String.valueOf(id));

        return new Customer(
                id, customerInfo.get(1),
                customerInfo.get(2),
                Integer.parseInt(customerInfo.get(3)),
                customerInfo.get(4)
        );
    }

    public static double getCustomerTypePrice(SoldItem soldItem) {
        return StaticContainer.currentSale.getCustomer() instanceof Retailer ?
                soldItem.getSoldItem().getPrice() :  //return normal price
                soldItem.getSoldItem().getBulkPrice();  //return bulk price
    }


    public static String getCurrentDateTime() {
        return java.time.LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
//blacklist customer for manage customer
    public static void blacklistedCustomer(int id) {
        FileHandler.writeFile(
                FileHandler.BLACKLIST_DB,
                String.format("%d,%s", id, getCurrentDateTime())
        );
    }

    public static void removeFromBlacklist(int id) {
        FileHandler.removeRowByID(FileHandler.BLACKLIST_DB, String.valueOf(id));
    }

    public static boolean checkCustomerIsBlacklisted(int id) {
        //check the customerID exist in blacklist or not
        ArrayList<String> result = FileHandler.getRowByMainID(FileHandler.BLACKLIST_DB, Integer.toString(id));
        return result.size() > 0;
    }

    public static void displayBlackList(){
        ArrayList<String[]> blacklist = FileHandler.readFileToArray(FileHandler.BLACKLIST_DB);
        blacklist.remove(0);  // remove the header
        System.out.println("\n-----------------------------------");
        System.out.println("\t\tBlacklisted Customer");
        System.out.println("-----------------------------------");
        if (blacklist.size() == 0) {
            System.out.println("No blacklisted customer!");
            System.out.println("-----------------------------------\n");
            return;
        }
        System.out.printf("%-12s%-20s\n", "Customer ID", "Blacklisted Date");
        for (String[] row : blacklist) {
            System.out.printf("%-12s%-20s\n", row[0], row[1]);
        }
        System.out.println("-----------------------------------\n");
    }

    public static void generateActivityLog(String username, double spend, int points) {
        String datetime = getCurrentDateTime();
        String activity = String.format("%s spent RM%.2f and earned %d points on store!", username, spend, points);
        String result = String.format("%s,%s", datetime, activity);
        FileHandler.writeFile(FileHandler.ACTIVITY_LOG, result);
    }

    public static void generateSalesLog(String username, String role, String itemName, int quantity) {
        String datetime = getCurrentDateTime();
        String activity = String.format("%s (%s) buy %d %s(s) on store!", username, role, quantity, itemName);
        String result = String.format("%s,%s", datetime, activity);
        FileHandler.writeFile(FileHandler.SALES_LOG, result);
    }

    public static void generatePaymentLog(String username, double amount, String paymentMethod) {
        String datetime = getCurrentDateTime();
        String activity = String.format("%s paid RM%.2f with payment type of %s!", username, amount, paymentMethod);
        String result = String.format("%s,%s", datetime, activity);
        FileHandler.writeFile(FileHandler.PAYMENTS_LOG, result);
    }

    public static double getItemPriceByRole(SoldItem soldItem, String role) {
        return role.equals("retailer") ? soldItem.getSoldItem().getPrice() : soldItem.getSoldItem().getBulkPrice();
    }
}
