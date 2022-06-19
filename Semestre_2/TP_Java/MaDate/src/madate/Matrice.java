/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package madate;

/**
 *
 * @author p2103455
 */
public class Matrice {
    private int collone,ligne;
    private float[][] tab ;

    public Matrice(int collone, int ligne) {
        this.collone = collone;
        this.ligne = ligne;
        this.tab = new float[collone][ligne];
    }

    public int getCollone() {
        return collone;
    }

    public void setCollone(int collone) {
        this.collone = collone;
    }

    public int getLigne() {
        return ligne;
    }

    public void setLigne(int ligne) {
        this.ligne = ligne;
    }

    public void setTab(float[][] tab) {
        this.tab = tab;
    }
    public void affiche(){
        for (int i = 0; i<collone;i++){
            for (int j = 0; i<ligne; i++){
                System.out.print(tab[i][j]);
            }
            System.out.println("");
        }
    }
    
}
