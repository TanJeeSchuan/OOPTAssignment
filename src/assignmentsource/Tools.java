package assignmentsource;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Tools {


//    public static String generateBarCode() {
//        //random 10 digit number
//        StringBuilder barcode = new StringBuilder();
//        for (int i = 0; i < 10; i++) {
//            barcode.append((int) (Math.random() * 10));
//        }
//        return barcode.toString();
//    }

    public static int getNewID(String db) {
        ArrayList<String[]> fileContents = FileHandler.readFileToArray(db);
        
        fileContents.remove(0);
        int largestInt = 1;
        for(String[] string: fileContents){
            if(Integer.parseInt(string[0])> largestInt)
                largestInt = Integer.parseInt(string[0]);
        }
        
        return largestInt+1;
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
        CSVFile.removeRowByID(FileHandler.BLACKLIST_DB, String.valueOf(id));
    }

//    public static boolean checkCustomerIsBlacklisted(int id) {
//        //check the customerID exist in blacklist or not
//        ArrayList<String> result = CSVFile.getRowByMainID(FileHandler.BLACKLIST_DB, Integer.toString(id));
//        return result.size() > 0;
//    }

//    public static void displayBlackList(ArrayList<Customer> blacklistCustomer) {
//        ArrayList<String[]> blacklist = FileHandler.readFileToArray(FileHandler.BLACKLIST_DB);
//        System.out.println("\n-----------------------------------");
//        System.out.println("\t\tBlacklisted Customer");
//        System.out.println("-----------------------------------");
//        if (blacklist.size() == 0) {
//            System.out.println("No blacklisted customer!");
//            System.out.println("-----------------------------------\n");
//            return;
//        }
//        System.out.printf("%-12s%-20s\n", "Customer ID", "Blacklisted Date");
//        for (String[] row : blacklist) {
//            System.out.printf("%-12s%-20s\n", row[0], row[1]);
//        }
//        System.out.println("-----------------------------------\n");
//    }

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
    
    public static int ROWS_TO_SHOW = 10;
    public static Selectable objectSelection(ArrayList<? extends Selectable> objectList) {
        Scanner sc = new Scanner(System.in);
        
        int selection = 0;
        int itemIndex = -1;
        
        if(objectList.isEmpty())
            throw new Runtime­Exception();
        
        for(int i = 0; i < objectList.size(); i+=ROWS_TO_SHOW){
            Selectable obj = objectList.get(0);
            System.out.print("\n" + obj.getFORMATHEADER() + "\n");
            for (int j = i; j < i + ROWS_TO_SHOW; j++)
            {
                if(j >= objectList.size())
                    break;
                
                System.out.print(objectList.get(j).toFormattedString());
                System.out.print("---------" + (j+1-i) + "\n");
            }                        
            boolean validSelection = false;
            do
            {
                System.out.printf("Enter selection (1 - 10), 11 for next page, 0 to exit: ");
                selection = sc.nextInt();
                
                if (selection == 11) //next input
                    if ((i + 10) < objectList.size())
                        break;
                    else
                        System.out.println("No Next Page!");
                    
                
                if (selection == 0){   //exit
                    validSelection = true;
                    break;
                }
                
                if (selection < 1 || selection > 10)
                {
                    System.out.println("Enter number within range\n");
                }
                else if(i + selection - 1 >= objectList.size()){
                    System.out.println("Selection out of range\n");
                }
                else
                {
                    validSelection = true;
                    itemIndex = i + selection - 1;
                }
                
            }while(!validSelection);
            
            if(validSelection == true)
                break;
        }
        
        if (selection != 0)
            return objectList.get(itemIndex);
        else
            return null;
    }
}
