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
            
            String[] s = {"Admin", "New User","Remove User", "Display All Users","Logout"};
            menuSel = Menu.menu(s);

            switch(menuSel){
                case 1:
                    users.add(newUser());
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
        username = sc.nextLine();
        
        do{
            System.out.print("\nEnter Password (min length 8): ");
            password = sc.nextLine();
        }while(password.length() < 8);
        
        System.out.print("\nEnter Name: ");
        name = sc.nextLine();
        
        System.out.print("\nEnter Phone Number: ");
        phoneNumber = sc.nextLine();
        
        int selection = 0;
        String[] s = {"Select new user type", "Cashier","Management", "Admin"};
        selection = Menu.menu(s);

        switch(selection){
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
