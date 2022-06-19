/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp1_ihm;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

/**
 *
 * @author p2103455
 */
public class Ma_Fenetre extends JFrame implements ActionListener, FocusListener{
    
    private JTextField text;
    private JButton convert;
    private JLabel  haut_droite, haut_gauche, bas;
    private JComboBox menu;
    
    public Ma_Fenetre() {
        this.setTitle("TP1_IHM");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        initialisation();
        
        convert.addActionListener(this);
        text.addFocusListener(this);
    }

    private void initialisation () {
         haut_gauche = new JLabel("nombre(en base 10)");
         haut_gauche.setBorder(new BevelBorder(1));
         haut_droite = new JLabel("base");
         haut_gauche.setBorder(new BevelBorder(1));
         
         text = new  JTextField("entrer un nombre");
         text.setColumns(10);
         
         menu  =  new JComboBox();
         menu.addItem("2");
         menu.addItem("3");
         menu.addItem("8");
         menu.addItem("16");
         
         convert =  new JButton("convert");
         
        bas = new JLabel("nombre converti");
        
        JPanel pano = new JPanel();
        pano.setLayout(new GridBagLayout());
        GridBagConstraints cons = new GridBagConstraints();
        
        cons.fill = GridBagConstraints.BOTH;
        
        cons.gridx = 0;
        cons.gridy=0;
        pano.add(haut_gauche,cons);
        
        cons.gridx=1;
        pano.add(haut_droite,cons);
        
        cons.gridx = 0;
        cons.gridy=1;
        pano.add(text,cons);
        
        cons.gridx = 1;
        cons.gridy=1;
        pano.add(menu,cons);
        
        cons.gridx = 0;
        cons.gridy=2;
        cons.gridwidth=2;
        pano.add(convert,cons);
        
        cons.gridx = 0;
        cons.gridy=3;
        pano.add(bas,cons);
        
        this.setContentPane(pano);
        this.pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == convert){
            
            int valeur = Integer.parseInt(text.getText());
            
            if (menu.getSelectedIndex()==0){
                bas.setText(Integer.toString(valeur, 2));
            }
            if (menu.getSelectedIndex()==1){
                bas.setText(Integer.toString(valeur, 3));
            }
            if (menu.getSelectedIndex()==2){
                bas.setText(Integer.toString(valeur, 8));
            }
            if (menu.getSelectedIndex()==3){
                bas.setText(Integer.toString(valeur, 16));
            }
        } 
    }

    @Override
    public void focusGained(FocusEvent e) {
        if(e.getSource() == text){
            text.setText("");
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        
    }
    
    
    
}
