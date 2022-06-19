/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2;

/**
 *
 * @author p2103455
 */
public class Case {
    private int couleur;

    public Case(int couleur) {
        this.couleur = couleur;
    }

    public int getCouleur() {
        return couleur;
    }

    public void setCouleur(int couleur) {
        this.couleur = couleur;
    }

    @Override
    public String toString() {
        String tmp = "";
        tmp = tmp + couleur;
        return tmp;
    }
    
    
    
    
}
