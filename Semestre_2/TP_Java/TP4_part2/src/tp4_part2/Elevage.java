/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp4_part2;

import java.util.ArrayList;

/**
 *
 * @author p2103455
 */
public class Elevage {
    private ArrayList<Volailles> liste;
    
    public void ajouterVolaille(Volailles v){
        liste.add(v);
    }
       
    public ArrayList<Volailles> abattre(){
        ArrayList<Volailles> tmp = new ArrayList<>();
        for (int i = 0; i < liste.size(); i++) {
            if (liste.get(i).aAbattre()){
                tmp.add(liste.get(i));
            }
        }
        liste.removeAll(tmp);
        return tmp;
    }
    
    public double prixRevient(){
        int prix_revient = 0;
        for (Volailles liste1 : liste) {
            prix_revient += liste1.PrixVente();
        }
        return prix_revient;
    }
    
}
