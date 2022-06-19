/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packageTP2;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author brice.effantin
 */
public class MaFenetre extends JFrame {
    MonComposant composant=new MonComposant();
    JButton btRaz=new JButton("RAZ");

    public MaFenetre(){
        this.setTitle("Premier composant");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //on place les 2 éléments
        JPanel pano=new JPanel();
        pano.setLayout(new GridBagLayout());
        GridBagConstraints gc=new GridBagConstraints();
        gc.fill=GridBagConstraints.BOTH;
        //on place le composant perso (on le gère comme tout autre composant)
        gc.gridx=0;
        gc.gridy=0;
        pano.add(composant,gc);
        //on place le bouton
        gc.gridx=0;
        gc.gridy=1;
        gc.insets=new Insets(10,3,3,3); //on crée des marges autour du bouton
        pano.add(btRaz,gc);

        this.setContentPane(pano);
        this.pack();

        //on ajoute le/les listener(s) utile(s)
        //à compléter
        composant.addMouseListener(composant);
        btRaz.addActionListener(composant);
    }
}
