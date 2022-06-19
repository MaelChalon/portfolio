/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp_part2;

import packVisu.FenetreVisuImage;

/**
 *
 * @author p2103455
 */
public class TP_part2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        
        PGM tmp = new PGM();
        tmp.load("Z:\\Documents\\IUT\\S2\\Java\\IHM\\TP_part2\\src\\tp_part2\\asset\\image1.pgm");
        
        //PGM fichier2 = new PGM(fichier1);
        
        //fichier2.save("fichier_save.PGM");
        tmp.affiche();
    }
    
}
