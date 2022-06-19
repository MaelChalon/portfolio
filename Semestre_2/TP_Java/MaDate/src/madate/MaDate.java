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
enum Jour{
    Dimanche,Lundi,Mardi,Mercredi,Jeudi,Vendredi,Samedi;
}
public class MaDate {
    private int jour,mois,annee;

    public MaDate() {
        jour=1;
        mois=1;
        annee=1970;
    }

    public MaDate(int jour, int mois, int annee) {
        this.jour = jour;
        this.mois = mois;
        this.annee = annee;
    }

    public void setJour(int jour) {
        this.jour = jour;
    }

    public void setMois(int mois) {
        this.mois = mois;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
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
    
    public int nomJour(){
        if (mois>=3){
            return((((23*mois)/9)+jour+4+annee+(annee/4)-(annee/100)+(annee/400)-2)%7);
        }
        else{
            return((((23*mois)/9)+jour+4+annee+(annee-1/4)-(annee-1/100)+(annee-1/400)-2)%7);
        }
    }
    @Override
    public String toString() {
        
        if(jour<10){
            if (mois<10){
                return (Jour.values()[nomJour()]+" 0"+jour+"/"+"0"+mois+"/"+annee+"/");
            }
            return (Jour.values()[nomJour()]+" 0"+jour+"/"+mois+"/"+annee+"/");
        }
        else {
            if (mois<10){
                return (Jour.values()[nomJour()]+" "+jour+"/"+"0"+mois+"/"+annee+"/");
            }
            return (Jour.values()[nomJour()]+" "+jour+"/"+mois+"/"+annee+"/");
        }
        
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Matrice m1;
                
    }

   
}
