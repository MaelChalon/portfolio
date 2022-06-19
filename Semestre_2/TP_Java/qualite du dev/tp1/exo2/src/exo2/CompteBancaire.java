/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exo2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author p2103455
 */
public class CompteBancaire {
    String iban;
    int solde=0;
    ArrayList<Operation> listeOperations;

    public CompteBancaire(String iban,int solde) {
        this.iban=iban;
        this.solde=solde;
        this.listeOperations = new ArrayList<>();
    }
    
    public void ajouterOperation(int montant, int j, int m, int a) throws MontantException, DateException{
        if(solde-montant >=0){
            if(montant>-10000){
                if (m>=1 && m<=12){
                    if (j>=1 && j<= 31){
                    solde = solde + montant;
                    this.listeOperations.add(new Operation(montant, j, m, a));   
                    }
                    else{
                        throw new DateException("Numero de jour illegale");
                    }
                }
                else{
                    throw new DateException("Numero de mois illegal");
                }
            }
            else{
                throw new MontantException("Montant supérieur a 10000€");
            }
        }
        else{
            throw new MontantException("Solde insufisant");
        }
    }

    public String getIban() {
        return iban;
    }

    public int getSolde() {
        return solde;
    }

    public ArrayList<Operation> getListeOperations() {
        return listeOperations;
    }
    
    public void affiche_liste_operation(){
        String resultat = "";
        for (Operation o : listeOperations) {
            resultat = resultat + o.getMontant() + "€ le " + o.getJour()+"/"+o.getMois()+"/"+o.getAnnee()+'\n';
        }
        System.out.println(resultat);
    }
    
    public int moyenne_par_mois(int m, int a){
        int moyenne=0;
        int cptr =0;
        for (Operation o : listeOperations) {
            if (o.getAnnee()==a && o.getMois()==m){
                moyenne += o.getMontant();
                cptr++;
            }
        }
        try {
            moyenne = moyenne/cptr;
            return moyenne;
        } catch (Exception e) {
            System.out.println("aucune transaction pour ce mois");
            return 0;
        }
        
    }
    
    public void importerOperations(String nom_fichier){
        FileInputStream fis=null;
        try {
            fis = new FileInputStream(nom_fichier);
            Scanner scan = new Scanner(fis);
            while (scan.hasNext()){
                String line=scan.nextLine();
                Scanner scan_line = new Scanner(line);
                int i=0;
                int mon =0;
                int j =0;
                int m=0;
                int a =0;
                while(i<4){
                    try {
                        if (i==0){
                            mon = Integer.parseInt(scan_line.next());
                            i++;
                        }
                        if (i==1){
                            j =Integer.parseInt(scan_line.next());
                            i++;
                        }
                        if (i==2){
                            m=Integer.parseInt(scan_line.next());
                            i++;
                        }
                        if (i==3){
                            a=Integer.parseInt(scan_line.next());
                            i++;
                        }
                    } 
                    catch (Exception e) {
                        System.out.println("entrez un int");
                    }
                }
                try {
                    this.ajouterOperation(mon, j, m, a);
                } catch (MontantException ex) {
                    System.out.println(ex.getMessage());
                } catch (DateException ex) {
                    System.out.println(ex.getMessage());
                }
                scan_line.close();
            }   
            scan.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CompteBancaire.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(CompteBancaire.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
