package com.example.tp_api.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Reseaux {

    @SerializedName("nhits")
    @Expose
    private int nhits;
    @SerializedName("records")
    @Expose
    private List<Record> records;

    public int getNhits() {
        return nhits;
    }

    public void setNhits(int nhits) {
        this.nhits = nhits;
    }

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }

}