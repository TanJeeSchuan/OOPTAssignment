//mingzhe

import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class StaffRegister {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try (sc) {

            boolean continueRegister = true;

            while (continueRegister) {

                System.out.print("Please enter a number of staff members: ");
                int numofStaff = sc.nextInt();
                sc.nextLine();
                Register[] staffArray = new Register[numofStaff];

                for (int i = 0; i < staffArray.length; i++) {

                    Register reg = new Register();    //instance

                    System.out.print("Please enter a name =>  ");
                    String Name = sc.nextLine();
                    while (!isValidName(Name)) {
                        System.out.print("Please enter a valid name =>  ");
                        Name = sc.nextLine();
                    }
                    reg.setName(Name);

                    System.out.print("Enter a password(6 to 8 character) => ");
                    String password = sc.nextLine();
                    while (!isValidPassword(password)) {
                        System.out.print("Invalid password. Please enter a valid password (between 6 to 8 character) => ");
                        password = sc.nextLine();
                    }
                    reg.setPassword(password);

                    System.out.print("Enter the staffID(Ex: S22012) => ");
                    String staffID = sc.nextLine();
                    while (!isValidID(staffID)) {
                        System.out.print("Invalid staff ID. Please enter a valid ID (starts with 'S', max 7 characters)=> ");
                        staffID = sc.nextLine();
                    }
                    reg.setStaffID(staffID);

                    System.out.print("Enter the phone number(Ex: 6016-1234567) => ");
                    String phoneNo = sc.nextLine();
                    while (!isValidPhone(phoneNo)) {
                        System.out.print("Enter the phone number(Ex: 6016-1234567) => ");
                        phoneNo = sc.nextLine();
                    }
                    reg.setPhoneNo(phoneNo);

                    System.out.print("Enter the email(Ex: abc123@gmail.com) => ");
                    String email = sc.nextLine();
                    while (!isValidEmail(email)) {
                        System.out.print("Enter the email(Ex: abc123@gmail.com) => ");
                        email = sc.nextLine();
                    }
                    reg.setEmail(email);

                    System.out.print("Enter the position => ");
                    String position = sc.nextLine();
                    reg.setPosition(position);

                    System.out.printf("\n");
                    System.out.println("Registration Form\n");
                    System.out.println(reg.toString());

                    staffArray[i] = reg;

                    try (FileWriter fileWriter = new FileWriter("Registration.txt", true)) {

                        fileWriter.write("\n");
                        fileWriter.write("Registration Form\n");
                        fileWriter.write(staffArray[i].toString());

                        System.out.println("User information has been written to Registration.txt....");
                    } catch (IOException e) {
                        System.out.println("An error occurred while writing to Registration.txt.");
                    }

                    System.out.print("Do you want to register more staff members? (yes/no): ");
                    String continueChoice = sc.nextLine();
                    if (continueChoice.equalsIgnoreCase("no")) {
                        continueRegister = false;
                    }

                }
            }
             System.out.println("Thank you for using the Staff Register.");

        }
    }

    //validation part
    //check staff name 
    public static boolean isValidName(String staffName) {
        return staffName != null && !staffName.isEmpty() && !staffName.matches(".*\\d.*");   //name must be all character and cannot be null
    }

    //check password 
    public static boolean isValidPassword(String password) {
        return password.length() >= 6 && password.length() <= 8;
    }

    //check staffID
    public static boolean isValidID(String staffID) {
        return staffID != null && staffID.length() <= 7 && staffID.matches("S[A-Za-z0-9]+");
    }

    public static boolean isValidPhone(String phone) {
        return phone != null && phone.length() >= 12 && phone.length() <= 13;
    }

    public static boolean isValidEmail(String email) {
        return email != null && email.matches("[A-Za-z0-9]+@[A-Za-z0-9]+\\.[A-Za-z]+");
    }
}

class Register {

    private String name;
    private String staffID;
    private String phoneNo;
    private String position;
    private String email;
    private String password;

    //getter or accessor
    public String getName() {
        return name;
    }

    public String getStaffID() {
        return staffID;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getPosition() {
        return position;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    //setter or mutator
    public void setName(String name) {
        this.name = name;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "1. StaffName: " + name + "\n"
                + "2. StaffPassword: " + password + "\n"
                + "3. StaffID: " + staffID + "\n"
                + "4. Staff Email: " + email + "\n"
                + "5. Phone No: " + phoneNo + "\n"
                + "6. Staff Position: " + position + "\n";

    }

}
