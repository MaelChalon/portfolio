package com.example.listviewandroid;

public class Team {

    String nom;
    String desc;
    int imageID;

    public Team(String n, int imgID){
        this.nom = n;
        this.imageID = imgID;
        this.desc = "description par default";
    }
}
