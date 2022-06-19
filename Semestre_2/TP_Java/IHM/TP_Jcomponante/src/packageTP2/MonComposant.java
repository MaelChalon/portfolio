/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packageTP2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.border.Border;

/**
 *
 * @author brice.effantin
 */
public class MonComposant  extends JComponent implements MouseListener, ActionListener {

    private ArrayList<Point>list;

    //constructeur par défaut
    public MonComposant(){
        list=new ArrayList<>();
    }

    @Override
    public Dimension getPreferredSize() {
        //si vous voulez un composant d'une taille différente, il faut modifier les valeurs
        return new Dimension(400,300); //dimension du composant
    }

    @Override
    protected void paintComponent(Graphics g) {
        //on dessine la ligne brisée
        //à compléter
        g.setColor(Color.red);
        for (int i = 1; i< list.size(); i++) {
            g.drawLine(list.get(i-1).x, list.get(i-1).y, list.get(i).x, list.get(i).y);
        }
        for (int j = 0; j < list.size(); j++) {
            g.drawLine(list.get(j).x+5, list.get(j).y+5, list.get(j).x-5, list.get(j).y-5);
            g.drawLine(list.get(j).x+5, list.get(j).y-5, list.get(j).x-5, list.get(j).y+5);
        }
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        //Si on reçoit un message de type MouseEvent quand on presse un bouton de souris
        //Alors on ajoute le point cliqué à la liste et on redessine le composant
        //à compléter
        list.add(new Point(mouseEvent.getX(), mouseEvent.getY()));
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }
    @Override

    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        //si on reçoit un événement de type Action, on efface tous les points;
        list.clear();
        repaint();
    }
}