/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp_part2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import packVisu.FenetreVisuImage;

/**
 *
 * @author p2103455
 */
public class PGM {
    
    public int largeur, hauteur;
    public int tab_2D[][];

    public PGM() {
    }

    public int getLargeur() {
        return largeur;
    }

    public int getHauteur() {
        return hauteur;
    }
    
    public PGM(PGM pgm) {
        this.largeur = pgm.largeur;
        this.hauteur = pgm.hauteur;
        
        this.tab_2D = new int[this.largeur][this.hauteur];
        for (int i = 0; i < largeur; i++) {
            for (int j = 0; j < hauteur; j++) {
                this.tab_2D[i][j]=pgm.tab_2D[i][j];
            }
        }
    }
    
    public void load(String fichier){
        File file = new File(fichier);
        Scanner scan = null;
        try {
            scan = new Scanner(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PGM.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        scan.nextLine();
        largeur= scan.nextInt();
        hauteur=scan.nextInt();
        
        scan.nextLine();
        scan.nextLine();
        int cptr=0;
        int tmp = 0;
        this.tab_2D = new int[largeur][hauteur];
        while(scan.hasNextInt()){
            if (cptr ==  hauteur){
                cptr = 0;
                tmp++;
            }
            this.tab_2D[tmp][cptr] = scan.nextInt();
            cptr++;
        }
    }
    
    public void save(String fichier){
        FileWriter fw = null;
        try {
            File file = new File(fichier);
            fw = new FileWriter(file);
            fw.write("P2"+'\n');
            fw.write(largeur + " " + hauteur +'\n');
            fw.write("255"+'\n');
            for (int i = 0; i < largeur; i++) {
                for (int j = 0; j < hauteur; j++) {
                    fw.write(this.tab_2D[i][j] + " ");
                }
                fw.write('\n');
            }
            fw.flush();
        } catch (IOException ex) {
            Logger.getLogger(PGM.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fw.close();
            } catch (IOException ex) {
                Logger.getLogger(PGM.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void affiche() throws Exception{
        FenetreVisuImage fenetre = new FenetreVisuImage("test");
        fenetre.setImage(hauteur, largeur, tab_2D);
        
    }
}
