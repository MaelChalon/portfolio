/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exo1;

import java.util.Scanner;

/**
 *
 * @author p2103455
 */
public class Exo1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Scanner scan;
        scan = new Scanner(System.in);
        
        System.out.println("entrer des entiers et terminé votre liste par 'fin': ");
        
        String valeur = new String();
        int moyenne = 0;
        int nb_valeur = 0;
        while (true){
            valeur = scan.next();
            if("fin".equals(valeur)){
                break;
            }
            try {
                moyenne =( moyenne + Integer.parseInt(valeur));
                nb_valeur++;
            }   
            catch (NumberFormatException e) {
                System.out.println("la valeur numero " + (nb_valeur+1) + " n'est pas un int");
            }
        }
        moyenne = moyenne/ nb_valeur;
        System.out.println(moyenne);
        
    }
    
}
