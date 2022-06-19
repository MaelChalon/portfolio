/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp4_part2;

/**
 *
 * @author p2103455
 */
public class TP4_part2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Elevage elevage1= new Elevage();
        Volailles.setCout(1.0);
        Poulet.setPoidsAbattage(1.5);
        Poulet.setPrixAuKilo(3.9);
        
        Canard.setPoidsAbattage(2.5);
        Canard.setPrixAuKilo(7.5);
        
        
    }
    
}
