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
public class Poulet extends Volailles{
    
    private static double prixAuKilo;
    private static double poidsAbattage;

    public Poulet(int id, double poids) {
        super(id, poids);
    }

    public Poulet() {
    }

    
    
    public static double getPrixAuKilo() {
        return prixAuKilo;
    }

    public static void setPrixAuKilo(double prixAuKilo) {
        Poulet.prixAuKilo = prixAuKilo;
    }

    public static double getPoidsAbattage() {
        return poidsAbattage;
    }

    public static void setPoidsAbattage(double poidsAbattage) {
        Poulet.poidsAbattage = poidsAbattage;
    }
    
    @Override
    public void evolutionHebdomadaire() {
        this.poids += (Math.random()%300)+200;
    }

    @Override
    public double prixAchat() {
        return 1.5;
    }

    @Override
    public double PrixVente() {
        if (aAbattre()){
            return poids * prixAuKilo - cout;
        }
        else{
            return -cout;
        }
    }

    @Override
    public boolean aAbattre() {
        return (poids >= poidsAbattage);
    }
    
}
