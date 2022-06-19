/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2;

import java.util.HashSet;

/**
 *
 * @author p2103455
 */
public class Grille {
    private Case[][] tab;
    private int hauteur, largeur;

    public Grille(int x, int y, int couleur){
        hauteur = x;
        largeur = y;
        tab = new Case[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                tab[i][j] = new Case(couleur);
            }
        }
    }
    
    public void changement_direction(Fourmis fourmis){
        if (tab[fourmis.getX()][fourmis.getY()].getCouleur() == 1){
            fourmis.setOrientation((fourmis.getOrientation()+1)%4);
        }
        else{
            if (fourmis.getOrientation()==0){
                fourmis.setOrientation(3);
            }
            else{
                fourmis.setOrientation(fourmis.getOrientation()-1);
            }
        }
    }
    
    public void changement_couleur(Fourmis fourmis){
        if (tab[fourmis.getX()][fourmis.getY()].getCouleur() == 1){
            tab[fourmis.getX()][fourmis.getY()].setCouleur(0);
        }
        else{
            tab[fourmis.getX()][fourmis.getY()].setCouleur(1);
        }
    }
    
    public void deplace(Fourmis fourmis){
        if (fourmis.getOrientation() == 0){
            if (fourmis.getX() == 0){
                fourmis.setOrientation(2);
            }
            else{
                fourmis.setX(fourmis.getX()-1);
            }
        }
        
        if (fourmis.getOrientation() == 1){
            if (fourmis.getY() == largeur-1){
                fourmis.setOrientation(3);
            }
            else {
                fourmis.setY(fourmis.getY()+1);
            }
        }
        if (fourmis.getOrientation() == 2){
            if (fourmis.getX() == hauteur-1){
                fourmis.setOrientation(0);
            }
            else{
                fourmis.setX(fourmis.getX()+1);
            }
        }
        if (fourmis.getOrientation() == 3){
            if(fourmis.getY() == 0){
                fourmis.setOrientation(1);
            }
            else{
                fourmis.setY(fourmis.getY()-1);
            }
        }
    }
    
    public String affiche(Fourmis f){
        String tmp ="";
        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < largeur; j++) {
                if (f.getX()== i && f.getY() == j){
                    tmp = tmp + tab[i][j] + f + '\t';
                }
                else{
                    tmp = tmp + tab[i][j] + '\t';
                }
            }
            tmp = tmp+'\n';
        }
        return tmp;
    }
    
    @Override
    public String toString() {
        String tmp ="";
        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < largeur; j++) {
                tmp = tmp + tab[i][j] + '\t';
            }
            tmp = tmp+'\n';
        }
        return tmp;
    }
    
    
    
}
