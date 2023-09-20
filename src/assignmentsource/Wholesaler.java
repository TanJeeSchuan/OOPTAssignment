package assignmentsource;


public class Wholesaler extends Customer {

    private final double points = 0.5;

    public Wholesaler(String name, String phoneNumber, String role) {
        super(name, phoneNumber, role);
    }

    public Wholesaler(int customerID, String name, String phoneNumber, int currentPoints, String role) {
        super(customerID, name, phoneNumber, currentPoints, role);
    }

    public static double getDeliveryFee(){
        return 150;
    }
}
