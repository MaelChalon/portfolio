/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Vue;

import Modele.Grille;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

/**
 *
 * @author eric.duchene
 */
public class VueGrille extends JComponent {
    Grille grille;
    int tailleCase;
    
    public VueGrille(Grille g){
        // a coder
    }
    
    public static Color getColor(int valeur){    
        //a modifier
        return null;
    }
    
    @Override
    protected void paintComponent(Graphics gra) {
        // a coder
    }
    
    @Override
    public Dimension getPreferredSize() {
        //a modifier
        return null;
    }


   
}
