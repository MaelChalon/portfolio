/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modele;

import java.util.ArrayList;

/**
 *
 * @author eric.duchene
 */
public class Grille {
    Case grille[][];
    int taille;
    
    
    public Grille(int taille){
        grille = new Case[taille][taille];
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                grille[i][j]= new Case(i,j);
            }
        }
        this.taille = taille;
        ajoutElementAlea();
        ajoutElementAlea();
    }
    
    public void reinitialiser(int taille){
        this.taille = taille;
        grille = new Case[taille][taille];
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                grille[i][j]= new Case(i,j);
            }
        }
        this.taille = taille;
        ajoutElementAlea();
        ajoutElementAlea();
    }
    
    public boolean ajoutElementAlea(){//return false si plein et pas possible
        ArrayList<Case> case_vide = new ArrayList<>();
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                if (grille[i][j].getValeur()==0){
                    case_vide.add(grille[i][j]);
                }
            }
        }
         //a ecrire
         if (case_vide.isEmpty()){
             return false;
         }
        else{
            case_vide.get((int)(Math.random()*100)%case_vide.size()).setValeur(((int)(Math.random()*100)%2+1)*2);
            return true;
        }
         
    }
    
    public Case[] getListeVertical(int indice_colonne){
        Case liste_resultat[] = new Case[taille];
        
        for (int i = 0; i < taille; i++) {
            liste_resultat[i]=grille[i][indice_colonne];
        }
        return liste_resultat;
    }
    
    public Case[] getListeHorizontal(int indice_ligne){
        Case liste_resultat[] = new Case[taille];
        
        for (int i = 0; i < taille; i++) {
            liste_resultat[i]=grille[indice_ligne][i];
        }
        return liste_resultat;
    }
    
    public void pousserGauche(){
        //a coder
        Case liste_a_modifier[];
        for (int i = 0; i < taille; i++) {
            liste_a_modifier = this.getListeHorizontal(i);
            for (int j = 1; j < taille; j++) {
                if (liste_a_modifier[j].getValeur()!= 0){
                    boolean modif = false;
                    for (int k = liste_a_modifier[j].getColonne()-1; k >= 0 ; k--) {
                        if(modif != true){
                            if(liste_a_modifier[k].getValeur()!=0 ){
                                if (liste_a_modifier[k].getValeur()==liste_a_modifier[j].getValeur()){
                                    liste_a_modifier[k].setValeur(liste_a_modifier[j].getValeur()*2);
                                    liste_a_modifier[j].setValeur(0);
                                    modif = true;
                                }
                                else{
                                    liste_a_modifier[k+1].setValeur(liste_a_modifier[j].getValeur());
                                    modif = true;
                                } 
                            }
                        }
                    }
                    if(modif != true){
                        liste_a_modifier[0].setValeur(liste_a_modifier[j].getValeur());
                        liste_a_modifier[j].setValeur(0);
                    }
                }
            }
        }
        ajoutElementAlea();
    }
    
    public void pousserHaut(){
        //a coder
        Case liste_a_modifier[];
        for (int i = 0; i < taille; i++) {
            liste_a_modifier = this.getListeVertical(i);
            for (int j = 1; j < taille; j++) {
                if (liste_a_modifier[j].getValeur()!= 0){
                    boolean modif = false;
                    for (int k = liste_a_modifier[j].getColonne()-1; k >= 0 ; k--) {
                        if(modif != true){
                            if(liste_a_modifier[k].getValeur()!=0 ){
                                if (liste_a_modifier[k].getValeur()==liste_a_modifier[j].getValeur()){
                                    liste_a_modifier[k].setValeur(liste_a_modifier[j].getValeur()*2);
                                    liste_a_modifier[j].setValeur(0);
                                    modif = true;
                                }
                                else{
                                    liste_a_modifier[k+1].setValeur(liste_a_modifier[j].getValeur());
                                    modif = true;
                                } 
                            }
                        }
                    }
                    if(modif != true){
                        liste_a_modifier[0].setValeur(liste_a_modifier[j].getValeur());
                        liste_a_modifier[j].setValeur(0);
                    }
                }
            }
        }
        ajoutElementAlea();
    }
    
     public void pousserBas(){
         //a coder
    }
     
     public void pousserDroite(){
         //a coder
         Case liste_a_modifier[];
        for (int i = 0; i < taille; i++) {
            liste_a_modifier = this.getListeHorizontal(i);
            for (int j = taille-2; j >= 0; j--) {
                if (liste_a_modifier[j].getValeur()!= 0){
                    boolean modif = false;
                    for (int k = liste_a_modifier[j].getColonne()+1; k <taille ; k++) {
                        if(modif != true){
                            if(liste_a_modifier[k].getValeur()!=0 ){
                                if (liste_a_modifier[k].getValeur()==liste_a_modifier[j].getValeur()){
                                    liste_a_modifier[k].setValeur(liste_a_modifier[j].getValeur()*2);
                                    liste_a_modifier[j].setValeur(0);
                                    modif = true;
                                }
                                else{
                                    liste_a_modifier[k-1].setValeur(liste_a_modifier[j].getValeur());
                                    modif = true;
                                } 
                            }
                        }
                    }
                    if(modif != true){
                        liste_a_modifier[taille-1].setValeur(liste_a_modifier[j].getValeur());
                        liste_a_modifier[j].setValeur(0);
                    }
                }
            }
        }
        ajoutElementAlea();
    }
    
     public int getTaille() {
        return taille;
    }
     
     public Case getCase(int i,int j){
         //a modifier
         return null;
     }
     
    @Override
     public String toString(){
        String resultat = "";
         for (Case[] c : grille) {
             for (Case c1 : c) {
                 resultat+=c1.getValeur()+" | ";
             }
             resultat+='\n';
         }
        return resultat;
     }
}
