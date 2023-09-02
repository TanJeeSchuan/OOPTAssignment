/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignmentsource;

/**
 *
 * @author Yeoh Ming Zhe
 */
public class SoldItem{
    
    public Item soldItem;
    public int quantity;
    
    public double calculate_subtotal()
    {
        return soldItem.getPrice() * quantity;
    }
    
}
