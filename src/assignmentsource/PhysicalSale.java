/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignmentsource;

/**
 *
 * @author Tan Jee Schuan
 */
public class PhysicalSale extends Cashier{
    private int counterID;
    
    static private int nextCounterID = 1;
    PhysicalSale()
    {
        this.counterID = nextCounterID;
        nextCounterID++;
    }

    /**
     * @return the counterID
     */
    public int getCounterID() {
        return counterID;
    }
}
