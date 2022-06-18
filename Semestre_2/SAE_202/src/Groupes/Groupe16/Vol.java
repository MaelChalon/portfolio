/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Groupes.Groupe16;

/**
 * cette classe permet de stocker l'ensemble des informations correspondant a un vols
 * @author chama
 */
public class Vol {
    private String identifiant;
    private int aeroportDepart;
    private int aeroportArrive;
    private int h_depart;
    private int min_depart;
    private int temps;

    public Vol() {
    }

    public Vol(String identifiant, int depart, int arrive, int h_depart, int min_depart, int temps) {
        this.identifiant = identifiant;
        this.aeroportDepart = depart;
        this.aeroportArrive = arrive;
        this.h_depart = h_depart;
        this.min_depart = min_depart;
        this.temps = temps;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public int getAeroportDepart() {
        return aeroportDepart;
    }

    public void setAeroportDepart(int aeroportDepart) {
        this.aeroportDepart = aeroportDepart;
    }

    public int getAeroportArrive() {
        return aeroportArrive;
    }

    public void setAeroportArrive(int aeroportArrive) {
        this.aeroportArrive = aeroportArrive;
    }

    public int getH_depart() {
        return h_depart;
    }

    public void setH_depart(int h_depart) {
        this.h_depart = h_depart;
    }

    public int getMin_depart() {
        return min_depart;
    }

    public void setMin_depart(int min_depart) {
        this.min_depart = min_depart;
    }

    public int getTemps() {
        return temps;
    }

    public void setTemps(int temps) {
        this.temps = temps;
    }
    
    
}
