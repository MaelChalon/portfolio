package model;

import java.io.Serializable;

public class Contact implements Serializable {

    private String name;
    private String surname;
    private String phone_number;
    private String genre;
    private int age;
    private String adresse;
    private int id;

    public String getName() {
        return name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getSurname() {
        return surname;
    }

    public String getGenre() {
        return genre;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getAdresse() {
        return adresse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
