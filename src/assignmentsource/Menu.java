/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignmentsource;

import java.util.Scanner;

/**
 *
 * @author Tan Jee Schuan, Cha Wan Xun
 */
public class Menu {
    
    public static int menu(String[] inputString){
        if(inputString.length < 3){
            System.out.println("Invalid Menu String");
            return -1;
        }
        
        int longestStringLength = 0;
        for(String s: inputString){
            if(s.length() > longestStringLength)
                longestStringLength = s.length();
        }
        
        String outputFormat = "%2d. %-" + longestStringLength + "s |\n";
        
        boolean firstRun = true;
        int selection = 0;
        do{
            if(!firstRun)
                System.out.println("\nInput Out Of Range");
            firstRun = false;
            
            System.out.print( "\n"+"-".repeat(longestStringLength + 8));
            System.out.println("\n      " + inputString[0]);   //print header     
            System.out.println("-".repeat(longestStringLength + 8));
            
            for(int i = 1; i < inputString.length; i++){
                System.out.printf(outputFormat, i, inputString[i]);
            }
            System.out.println("");
            selection = getIntegerInput();
        }while(selection < 1 || selection >= inputString.length);
        
        return selection;
    }
    
    public static int getIntegerInput(){
        Scanner sc = new Scanner(System.in);
       
        int input = -1;
        while (true) {
            try {
                input = Integer.parseInt(sc.nextLine());
                return input;
            } catch (NumberFormatException e) {
                System.out.println("Input Not Integer\n");
            }
        }
    }

    public static String getStringInput(){
        Scanner sc = new Scanner(System.in);
        String input;
        while (true) {
            input = sc.nextLine();
            if(input.length() > 0){
                break;
            }
            System.out.println("Enter a String\n");
        }
        return input;
    }

    public static double getDoubleInput(){
        Scanner sc = new Scanner(System.in);
        double input;
        while (true) {
            try {
                input = Double.parseDouble(sc.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, please try again\n");
            }
        }
        return input;
    }
}
