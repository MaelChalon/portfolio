/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exo2;

/**
 *
 * @author p2103455
 */
public class Operation {
    int montant;
    int jour,mois,annee;

    public Operation(int montant, int jour, int mois, int annee) {
        this.montant = montant;
        this.jour = jour;
        this.mois = mois;
        this.annee = annee;
    }

    public int getMontant() {
        return montant;
    }

    public int getJour() {
        return jour;
    }

    public int getMois() {
        return mois;
    }

    public int getAnnee() {
        return annee;
    }
    
    
    
    
}
