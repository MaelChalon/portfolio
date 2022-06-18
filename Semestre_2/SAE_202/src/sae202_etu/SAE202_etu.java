/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sae202_etu;

import Groupes.Groupe16.Aeroport;
import Groupes.Groupe16.Groupe16;
import Groupes.Groupe16.Vol;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import org.graphstream.graph.Graph;

/**
 *
 * @author brice.effantin
 */
public class SAE202_etu {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        Groupe16 gp = new Groupe16();
        
        gp.modelisation("model-test", 10, (long)10000);
        
    }

   
    
}
