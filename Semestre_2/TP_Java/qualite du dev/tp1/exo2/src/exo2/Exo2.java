/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exo2;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author p2103455
 */
public class Exo2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int action = 50;
        CompteBancaire compte = new CompteBancaire("12545254", 1000);
        for (int i = 1; i <= 10; i++) {
            int mon = i*10;
            
            try {
            compte.ajouterOperation(mon, i, i, 2000+i);
            
            } catch (MontantException ex) {
                System.out.println(ex.getMessage());
            } catch (DateException ex) {
                System.out.println(ex.getMessage());
            }
        }
        Scanner scan;
        scan = new Scanner(System.in);
            
        while (true){
            System.out.println("pour afficher votre solde tapez 1");
            System.out.println("pour ajouter une operation tapez 2");
            System.out.println("pour calculer la moyenne des transaction sur un mois tapez 3");
            System.out.println("pour afficher la liste des transaction tapez 4");
            System.out.println("pour ajouter des operation depuis un document tapez 5");
            System.out.println("pour quitter ce menu tapez 6");
            
            try {
                action = Integer.parseInt(scan.next());
            } catch (NumberFormatException e) {
                System.out.println("la valeur entree n'est pas un int");
                action = 50;
            }
            
            
            if (action == 1 ){
                System.out.println(compte.getSolde());
            }
            if (action == 2){
                int montant =0;
                int jour = 0;
                int mois = 0;
                int annee =0;
                System.out.println("entrez le montant puis le jour puis le mois et enfin l'annee");
                int i=0;
                int tmp=0;
                while (i<4){
                    try {
                        
                        tmp = Integer.parseInt(scan.next());
                        if (i==0){
                            montant = tmp;
                        }
                        if(i==1){
                            jour = tmp;
                        }
                        if (i==2){
                            mois = tmp;
                        }
                        if(i==3){
                            annee = tmp;
                        }
                        i++;
                        
                    } 
                    catch (NumberFormatException e) {
                        System.out.println("entrez un int");
                    }
                }
                
                
                try {
                    compte.ajouterOperation(montant,jour,mois,annee);

                } catch (MontantException ex) {
                    System.out.println(ex.getMessage());
                } catch (DateException ex) {
                    System.out.println(ex.getMessage());
                }
            }
            
            if(action == 3){
                int i =0;
                int mois =0;
                int annee = 0;
                System.out.println("entrez le mois puis l'annee ");
                while (i<2){
                    try {
                        if(i==0){
                            mois = Integer.parseInt(scan.next());
                            i++;
                        }
                        if (i==1){
                            annee = Integer.parseInt(scan.next());
                            i++;
                        }
                    }
                    catch (NumberFormatException e) {
                        System.out.println("entrez un int");
                    }
                }
                System.out.println(compte.moyenne_par_mois(mois, annee));
            }
            if(action==4){
                compte.affiche_liste_operation();
            }
            if (action == 5){
                System.out.println("entrer l'adresse du fichier");
                String path = scan.next();
                compte.importerOperations(path);
            }
            if (action ==6){
                break;
            }
        }
        
//        CompteBancaire compte = new CompteBancaire("12545254", 1000);
//        compte.importerOperations("Z:\\Documents\\IUT\\S2\\Java\\qualite du dev\\tp1\\exo2\\liste1.txt");
//        compte.affiche_liste_operation();
    }
  
}
