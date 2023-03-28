package com.example.tp5.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class CompetitionListe {

    @SerializedName("country_name")
    @Expose
    private String countryName;
    @SerializedName("country_logo")
    @Expose
    private String countryLogo;
    @SerializedName("competitions")
    @Expose
    private List<Competition> competitions;

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryLogo() {
        return countryLogo;
    }

    public void setCountryLogo(String countryLogo) {
        this.countryLogo = countryLogo;
    }

    public List<Competition> getCompetitions() {
        return competitions;
    }

    public void setCompetitions(List<Competition> competitions) {
        this.competitions = competitions;
    }
}
