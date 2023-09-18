/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignmentsource;

import java.time.LocalDate;

/**
 *
 * @author Tan Jee Schuan
 */
public abstract class User {
    private String username;
    private String password;
    
    private String name;
    private LocalDate birthDate;
    private String phoneNumber;
    
    User(String[] data)
    {
        this(data[1], data[2], data[3], data[4], data[5]);
    }
    
    User(String username, String password, String name, String birthDate, String phoneNumber)
    {
        this.username = username;
        this.password = password;
        this.name = name;
        this.birthDate = LocalDate.parse(birthDate);
        this.phoneNumber = phoneNumber;
    }
    
    @Override
    public String toString()
    {
        return username + " " + password + " " +name + " " + birthDate.toString() + " " + phoneNumber; 
    }
    
    public User checkLogin(String inputUsername, String inputPassword){
        if (checkUsername(inputUsername) && checkPassword(inputPassword))
            return this;
        
        else
            return null;
    }
    
    private boolean checkUsername(String inputUsername){
        return username.equals(inputUsername);
    }
    
    private boolean checkPassword(String inputPassword){
        return password.equals(inputPassword);
    }
    
    //permissions?
    public abstract void mainMenu();
}
