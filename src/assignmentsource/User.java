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
public abstract class User implements Selectable{
    public final static String[] FILE_HEADER = {"role,username,password,name,birthDate,phoneNumber"};
    public final static String STRING_FORMAT = "%-15s%-20s%-20s%-30s%-15s%-15s";
    public final static String FORMAT_HEADER = String.format(STRING_FORMAT, "Role", "Username", "Password", "Name", "Birth Date", "Phone Number");
    
    protected String username;
    protected String password;
    
    protected String name;
    protected LocalDate birthDate;
    protected String phoneNumber;
    
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
    
    @Override
    public String[] getFILEHEADER() {
        return FILE_HEADER;
    }
    @Override
    public String getSTRINGFORMAT() {
        return STRING_FORMAT;
    }
    @Override
    public String getFORMATHEADER() {
        return FORMAT_HEADER;
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
    
    public abstract void mainMenu();
}
