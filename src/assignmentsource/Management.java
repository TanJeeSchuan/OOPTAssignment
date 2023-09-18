/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignmentsource;

/**
 *
 * @author Tan Jee Schuan
 */
public class Management extends User{
    
    public Management(String[] data){
        super(data);
    }
    
    @Override
    public void mainMenu() {
        System.out.println("Management Class");
    }
    
}
