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


public class Fourmis {
    private int x, y;
    private int orientation;
    

    public Fourmis(int x, int y, int ori) {
        this.x = x;
        this.y = y;
        this.orientation = ori;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getOrientation() {
        return orientation;
    }

    @Override
    public String toString() {
        String tmp = "";
        if (orientation == 0){
            tmp = tmp + "↑";
        }
        if (orientation == 1){
            tmp = tmp + "→";
        }
        if (orientation == 2){
            tmp = tmp + "↓";
        }
        if (orientation == 3){
            tmp = tmp + "←";
        }
        return tmp;
    }
    
    
    
}
