/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignmentsource;

/**
 *
 * @author Tan Jee Schuan
 */
public class Admin extends User{
    
    public Admin(String[] data){
        super(data);
    }
    
    @Override
    public boolean mainMenu() {
        System.out.println("Admin Class");
        
        return false;
    }
    
    public String toFormattedString(){
        return String.format(STRING_FORMAT,"Admin",username, password, name, birthDate.toString(), phoneNumber);
    }
}
