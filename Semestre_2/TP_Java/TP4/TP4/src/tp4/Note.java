/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp4;

import java.util.Objects;

/**
 *
 * @author p2103455
 */
public class Note implements Comparable{
    
    private String nom;
    private Double valeur;

    public Note(String nom, Double valeur) {
        this.nom = nom;
        this.valeur = valeur;
    }

    public Note( Note n) {
        this.nom = n.getNom();
        this.valeur = n.getValeur();
    }

    public Note() {
    }
    
    public String getNom() {
        return nom;
    }

    public Double getValeur() {
        return valeur;
    }

    @Override
    public String toString() {
        return "Note{" + "nom=" + nom + ", valeur=" + valeur + '}';
    }

//    @Override
//    public int compareTo(Object o) {
//        if (this.getClass() != o.getClass()){
//            return 0;
//        }
//        else{
//            Note tmp = new Note((Note)o);
//            return this.nom.compareTo(tmp.nom);
//        }
//    }
    
    @Override
    public int compareTo(Object o) {
        if (this.getClass() != o.getClass()){
            return 0;
        }
        else{
            Note tmp = new Note((Note)o);
            return this.valeur.compareTo(tmp.valeur);
        }
    }

    @Override
    public boolean equals(Object obj){ 
        if (this.getClass() == obj.getClass()){
            Note tmp = new Note((Note)obj);
            if(tmp.getNom() == null ? this.getNom() == null : tmp.getNom().equals(this.getNom())){
                return (Objects.equals(tmp.getValeur(), this.getValeur()));
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.nom);
        hash = 41 * hash + Objects.hashCode(this.valeur);
        return hash;
    }
}
