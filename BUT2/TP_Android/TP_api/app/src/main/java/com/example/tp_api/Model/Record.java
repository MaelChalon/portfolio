package com.example.tp_api.Model;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.maps.android.clustering.ClusterItem;

import java.io.Serializable;

public class Record implements Serializable, ClusterItem {

    @SerializedName("recordid")
    @Expose
    private String recordid;
    @SerializedName("fields")
    @Expose
    private Fields fields;

    public String getRecordid() {
        return recordid;
    }

    public void setRecordid(String recordid) {
        this.recordid = recordid;
    }

    public Fields getFields() {
        return fields;
    }

    public void setFields(Fields fields) {
        this.fields = fields;
    }


    @Override
    public LatLng getPosition() {
        return new LatLng(fields.getCoordonnees().get(0), fields.getCoordonnees().get(1));
    }

    @Override
    public String getTitle() {
        return fields.getTechnologies();
    }

    @Override
    public String getSnippet() {
        return fields.getNomOp();
    }
}

