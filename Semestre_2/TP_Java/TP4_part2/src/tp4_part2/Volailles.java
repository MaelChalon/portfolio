/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp4_part2;

/**
 *
 * @author p2103455
 */
public abstract class Volailles {
    protected static double cout;
    protected int id;
    protected double poids;

    public Volailles(int id, double poids) {
        this.id = id;
        this.poids = poids;
    }

    public Volailles() {
    }

    public static double getCout() {
        return cout;
    }

    public static void setCout(double cout) {
        Volailles.cout = cout;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPoids() {
        return poids;
    }

    public void setPoids(double poids) {
        this.poids = poids;
    }
    
    public abstract void evolutionHebdomadaire();
    
    public abstract double prixAchat();
    public abstract double PrixVente();
    public abstract boolean aAbattre();
    
}
