package com.example.tp_api.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.tp_api.Model.Fields;
import com.example.tp_api.Model.ListRecord;
import com.example.tp_api.Model.Record;
import com.example.tp_api.R;

import java.util.ArrayList;

public class ReseauAdapter extends BaseAdapter {

    ListRecord listRecord;

    public ReseauAdapter (ListRecord list){
        listRecord = list;
    }

    @Override
    public int getCount() {

        if(listRecord.afficheFavorite()){
            return listRecord.getListRecordFavorite().size();
        }else{
            return listRecord.getListRecord().size();
        }


    }

    @Override
    public Object getItem(int position) {

        if(listRecord.afficheFavorite()){
            return listRecord.getListRecordFavorite().get(position);
        }else{
            return listRecord.getListRecord().get(position);
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Record r;
        if(listRecord.afficheFavorite()){
            r = listRecord.getListRecordFavorite().get(position);
        }else{
            r = listRecord.getListRecord().get(position);
        }

        Fields field = r.getFields();

        ConstraintLayout layout = new ConstraintLayout(parent.getContext());
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        if(convertView == null){
            layout = (ConstraintLayout) inflater.inflate(R.layout.adapter_reseaux_item, parent, false);
        }else{
            layout = (ConstraintLayout) convertView;
        }



        TextView tv = layout.findViewById(R.id.technologies);
        tv.setText(field.getTechnologies());

        tv = layout.findViewById(R.id.insee_com);
        tv.setText(field.getNomCom());

        tv = layout.findViewById(R.id.nom_com);
        tv.setText(field.getNomDep());

        ImageView favorite = layout.findViewById(R.id.favorite);
        if(listRecord.isFavorite(r)){
            favorite.setImageDrawable(favorite.getResources().getDrawable(R.drawable.baseline_favorite_25));
        }else{
            favorite.setImageDrawable(favorite.getResources().getDrawable(R.drawable.baseline_favorite_24));
        }

        return layout;
    }
}
