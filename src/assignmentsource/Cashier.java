/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignmentsource;

/**
 *
 * @author Tan Jee Schuan
 */
public class Cashier{
    private String employeeName;
    private String ICNumber;
    private String phoneNumber;
    
    Cashier()
    {
        this.employeeName = "Test Name";
        this.ICNumber = "IC TEST";
        this.phoneNumber = "123453625";
    }

    /**
     * @return the employeeName
     */
    public String getEmployeeName() {
        return employeeName;
    }

    /**
     * @param employeeName the employeeName to set
     */
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    /**
     * @return the ICNumber
     */
    public String getICNumber() {
        return ICNumber;
    }

    /**
     * @param ICNumber the ICNumber to set
     */
    public void setICNumber(String ICNumber) {
        this.ICNumber = ICNumber;
    }

    /**
     * @return the phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber the phoneNumber to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
