/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2;

/**
 *
 * @author p2103455
 */
public class Polygone {
    private Point[] tab;

    public Polygone(Point pt) {
        this.tab = new Point[2];
        tab[0] = pt;
        tab[1] = pt;
    }
    
    
    
    public void addPoint(Point pt){
        Point[] tmp = tab;
        tab = new Point[tmp.length+1];
        System.arraycopy(tmp, 0, tab, 0, tmp.length-1);
        tab[tmp.length-1] = pt;
        tab[tmp.length] = tmp[tmp.length-1];
    }

    @Override
    public String toString() {
        String tmp = "";
        for (Point tab1 : tab) {
            tmp = tmp + tab1 + ",";
        }
        return tmp;
    }
    
}
