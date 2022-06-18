package sae.pkg204.Vue;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * cette classe permet de concerver l'ensemble des information en rapport avec un utilisateur.
 * @author chama
 */
public class Utilisateur {

    String nom;
    int role;
    String mot_de_passe;
    
    public Utilisateur() {
        this.nom = new String();
        this.mot_de_passe = new String();
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getMot_de_passe() {
        return mot_de_passe;
    }

    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }
    
    
    @Override
    public String toString() {
        String tmp = this.nom + " " + this.role;
        return tmp;
    }
    
}
