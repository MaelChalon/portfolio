/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modele;

/**
 *
 * @author eric.duchene
 */
public class Case {
    int ligne,colonne;
    int valeur; //si 0: case vide

    public int getLigne() {
        return ligne;
    }

    public void setLigne(int ligne) {
        this.ligne = ligne;
    }

    public int getColonne() {
        return colonne;
    }

    public void setColonne(int colonne) {
        this.colonne = colonne;
    }

    public Case(int ligne, int colonne) {
        this.ligne = ligne;
        this.colonne = colonne;
        this.valeur = 0;
    }

    public Case(int ligne, int colonne, int valeur) {
        this.ligne = ligne;
        this.colonne = colonne;
        this.valeur = valeur;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

    public int getValeur() {
        // a modifier
        return this.valeur;
    }
    
}
