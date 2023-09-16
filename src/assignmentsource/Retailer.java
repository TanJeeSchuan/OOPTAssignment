package assignmentsource;

public class Retailer extends Customer {

    public Retailer(int customerID) {
        super(customerID);
    }

    public Retailer(String name, String phoneNumber, String role) {
        super(name, phoneNumber, role);
    }

    public Retailer(int customerID, String name, String phoneNumber, int currentPoints, String role) {
        super(customerID, name, phoneNumber, currentPoints, role);
    }

    public static double getDiscount(double points){
        return Math.floor(points / 200);  //each 200 points discount 1
    }
}
