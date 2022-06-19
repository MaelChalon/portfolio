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
public class Canard extends Volailles {
    private static double prixAuKilo;
    private static double poidsAbattage;

    public Canard(int id, double poids) {
        super(id, poids);
    }

    public Canard() {
    }

    public static double getPrixAuKilo() {
        return prixAuKilo;
    }

    public static void setPrixAuKilo(double prixAuKilo) {
        Canard.prixAuKilo = prixAuKilo;
    }

    public static double getPoidsAbattage() {
        return poidsAbattage;
    }

    public static void setPoidsAbattage(double poidsAbattage) {
        Canard.poidsAbattage = poidsAbattage;
    }
    
    
    
    @Override
    public void evolutionHebdomadaire() {
        this.poids += (Math.random()%350)+200;
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
