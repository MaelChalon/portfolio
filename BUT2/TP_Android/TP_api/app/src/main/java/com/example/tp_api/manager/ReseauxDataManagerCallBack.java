package com.example.tp_api.manager;

import com.example.tp_api.Model.Reseaux;

public interface ReseauxDataManagerCallBack {
    void getReseauxResponseSuccess(Reseaux reseaux);
    void getReseauxResponseError(String message);
}

