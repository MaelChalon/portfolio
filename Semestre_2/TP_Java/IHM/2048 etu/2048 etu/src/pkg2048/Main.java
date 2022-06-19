/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pkg2048;

import Modele.Grille;




/**
 *
 * @author eric.duchene
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Grille grille=new Grille(4);
        
        System.out.println(grille);
        
        grille.pousserHaut();
        System.out.println(grille);
        grille.pousserHaut();
        System.out.println(grille);
        grille.pousserHaut();
        System.out.println(grille);
        grille.pousserHaut();
        System.out.println(grille);
        
//        grille.pousserGauche();
//        grille.ajoutElementAlea();
//        System.out.println("gauche\n"+grille);
//        
//        grille.pousserDroite();
//        grille.ajoutElementAlea();
//        System.out.println("droite\n"+grille);
//        
//        grille.pousserGauche();
//        grille.ajoutElementAlea();
//        System.out.println("gauche\n"+grille);
//        
//        grille.pousserHaut();
//        grille.ajoutElementAlea();
//        System.out.println("haut\n"+grille);
//        
//        grille.pousserBas();
//        grille.ajoutElementAlea();
//        System.out.println("bas\n"+grille);
        
        
    }
    
}
