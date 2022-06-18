/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Groupes.Groupe16;

/**
 * cette classe permet de stocker l'ensemble des information relative a un aeroport.
 * @author chama
 */
public class Aeroport {
    private String code;
    private String nom;
    private int degres_latitude;
    private int minute_latitude;
    private int seconde_latitude;
    private int degres_longitude;
    private int minute_longitude;
    private int seconde_longitude;
    private String latitude;
    private String longitude;
    private double latitude_degres_decimal;
    private double longitude_degres_decimal;
    private double x;
    private double y;
    
    public Aeroport() {
    }

    public Aeroport(String code, String nom, int degres_latitude, int minute_latitude, int seconde_latitude, int degres_longitude, int minute_longitude, int seconde_longitude, String latitude, String longitude) {
        this.code = code;
        this.nom = nom;
        this.degres_latitude = degres_latitude;
        this.minute_latitude = minute_latitude;
        this.seconde_latitude = seconde_latitude;
        this.degres_longitude = degres_longitude;
        this.minute_longitude = minute_longitude;
        this.seconde_longitude = seconde_longitude;
        this.latitude = latitude;
        this.longitude = longitude;
        
        this.set_degres_decimal();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getDegres_latitude() {
        return degres_latitude;
    }

    public void setDegres_latitude(int degre_latitude) {
        this.degres_latitude = degre_latitude;
    }

    public int getMinute_latitude() {
        return minute_latitude;
    }

    public void setMinute_latitude(int minute_latitude) {
        this.minute_latitude = minute_latitude;
    }

    public int getSeconde_latitude() {
        return seconde_latitude;
    }

    public void setSeconde_latitude(int second_latitude) {
        this.seconde_latitude = second_latitude;
    }

    public int getDegres_longitude() {
        return degres_longitude;
    }

    public void setDegres_longitude(int degre_longitude) {
        this.degres_longitude = degre_longitude;
    }

    public int getMinute_longitude() {
        return minute_longitude;
    }

    public void setMinute_longitude(int minute_longitude) {
        this.minute_longitude = minute_longitude;
    }

    public int getSecond_longitude() {
        return seconde_longitude;
    }

    public void setSeconde_longitude(int seconde_longitude) {
        this.seconde_longitude = seconde_longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public double getLatitude_degres_decimal() {
        return latitude_degres_decimal;
    }

    public void setLatitude_degres_decimal(double latitude_degres_decimal) {
        this.latitude_degres_decimal = latitude_degres_decimal;
    }

    public double getLongitude_degres_decimal() {
        return longitude_degres_decimal;
    }

    public void setLongitude_degres_decimal(double longitude_degres_decimal) {
        this.longitude_degres_decimal = longitude_degres_decimal;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
    
    /**
     * cette methode convertis la position en degres dse aeroport en degres decimal(radiant). Elle les consserve également dans les attribut correspondant.
     */
    public void set_degres_decimal(){
        if (latitude.equals("N")){
            this.latitude_degres_decimal = ((double)(degres_latitude) + ((double)minute_latitude/(double)60) + ((double)seconde_latitude/(double)3600))* Math.PI/180;
        }
        if (latitude .equals("S")){
            this.latitude_degres_decimal = (-1*((double)(degres_latitude) + ((double)minute_latitude/(double)60) + ((double)seconde_latitude/(double)3600))) *Math.PI/180;
        }
        if (longitude.equals("E")){
           this.longitude_degres_decimal = ((double)(degres_longitude) + ((double)minute_longitude/60) + ((double)seconde_longitude/(double)3600))*Math.PI/180; 
        }
        if (longitude.equals("O")){
            this.longitude_degres_decimal = (-1*((double)(degres_longitude) + ((double)minute_longitude/(double)60) + ((double)seconde_longitude/(double)3600)))*Math.PI/180; 
        }
    }
    
    /**
     * cette methode permet de convertir les degrès decimal en position X et Y d'un plan plat.
     */
    public void set_x_y(){
        this.x= 6371 * Math.cos(this.latitude_degres_decimal) *Math.sin(longitude_degres_decimal);
        this.y = 6371 * Math.cos(this.latitude_degres_decimal) * Math.cos(longitude_degres_decimal);
    }
}
