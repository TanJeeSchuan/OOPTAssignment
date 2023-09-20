/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package assignmentsource;

/**
 *
 * @author Tan Jee Schuan
 */

//classes that implement this function can be selected using  the    public static Object objectSelection(ArrayList<?> objectList) method
public interface Selectable {    
   
    public String toFormattedString();
    
    public String[] getFILEHEADER();
    public String getSTRINGFORMAT();
    public String getFORMATHEADER();
}
