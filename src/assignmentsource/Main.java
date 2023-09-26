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
public class Main {    
    public static void main(String[] args)
    {
        StationaryPOS sPOS = StationaryPOS.getInstance();        //initialse singleton
                
        Scanner sc = new Scanner(System.in);
        
        ArrayList<User>users = Init.initUsers();
       
        while(true){
            User foundUser = null;
            do{
                System.out.println("-----------------------------------");
        System.out.println("\t   Login Menu");
        System.out.println("-----------------------------------");
        
                System.out.print("\nUsername: ");
                String inputUsername = sc.next();

                System.out.print("Password: ");
                String inputPassword = sc.next();
                
                for (User u : users){
                    foundUser = u.checkLogin(inputUsername, inputPassword);
                    if (foundUser != null)
                        break;
                }
                if (foundUser != null)
                    break;
                System.out.println("\nWrong Username/Password!\n");
            }while(true);

            foundUser.mainMenu();

            String quitSel = null;
            do{
                System.out.print("\nDo You Want To Quit The System? (y/n): ");
                quitSel = sc.next();
            }while(!("y".equals(quitSel)) && !("n".equals(quitSel)));

            if ("y".equals(quitSel))
                break;
        }
    }
}
