/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author p2103455
 */
public class TP2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Grille grille = new Grille(3, 3, 1);
        Fourmis fourmis = new Fourmis(1,1,1);
        System.out.println(grille.affiche(fourmis));
        
        int cptr=0;
        while(cptr != 10){
            grille.changement_direction(fourmis);
            grille.changement_couleur(fourmis);
            grille.deplace(fourmis);
            System.out.println(grille.affiche(fourmis));
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                   }
        }
        
    }
    
}
