/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignmentsource;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Tan Jee Schuan
 */
public class Admin extends User{
    
    public Admin(String[] data){
        super(data);
    }
    
    public Admin (String username, String password, String name, String phoneNumber){
        super(username, password, name, phoneNumber);
    }
    
    @Override
    public boolean mainMenu() {
        Scanner sc = new Scanner(System.in);
                
        ArrayList<User> users = Init.initUsers();
        
        int menuSel = 0;
        do{
            System.out.print("\nAdmin\n1. New User\n2. Remove User\n3. Display All Users\n4. Logout\n\n");
            switch(sc.nextInt())
            {
                case 1:
                    newUser();
                    break;

                case 2:
                    User u = (User)Tools.objectSelection(users);
                    if(u != null){
                        deleteUser(u);
                        users.remove(u);
                    }
                    else
                        System.out.println("\nNo User Selected");
                    break;
                    
                case 3:
                    displayAllUsers(users);
                    break;
                    
                case 4:
                    return true;
            }
        }while(menuSel != 4);        
        return false;
    }
    
    public User newUser(){
        Scanner sc = new Scanner(System.in);
        
        User newUser = null;
        
        String username = "";
        String password = "";
        String name;
        String phoneNumber;
        
        System.out.print("\nEnter Username: ");
        username = sc.next();
        
        do{
            System.out.print("\nEnter Password (min length 8): ");
            password = sc.next();
        }while(password.length() < 8);
        
        System.out.print("\nEnter Name: ");
        name = sc.next();
        
        System.out.print("\nEnter Phone Number: ");
        phoneNumber = sc.next();
        
        int selection = 0;
        do{
            System.out.print("\nSelect new user type\n1.Cashier\t\t2.Management\t\t3.Admin\nSelection: ");
            selection = sc.nextInt();
        }while(selection < 1 || selection > 3);
        
        switch (selection) {
            case 1:
                newUser = new Cashier(username, password, name, phoneNumber);
                break;
            case 2:
                newUser = new Management(username, password, name, phoneNumber);
                break;
            case 3:
                newUser = new Admin(username, password, name, phoneNumber);
                break;
        }
        
        
        FileHandler.writeFile(FileHandler.USER_DB, newUser.toCSV());
        return newUser;
    }
    
    public void deleteUser(User u){
        CSVFile.removeRow(FileHandler.USER_DB, u.toCSV());
    }
    
    public void displayAllUsers(ArrayList<User> users){
        System.out.println(User.FORMAT_HEADER);
        for (User u : users){
            System.out.println(u.toFormattedString());
        }
    }
    
    public String toFormattedString(){
        return String.format(STRING_FORMAT,"Admin",username, password, name, phoneNumber);
    }
    
    public String toCSV(){
        return "Admin," + super.toCSV();
    }
}
