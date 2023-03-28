package com.example.listviewandroid;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;

public class TeamAdapter extends BaseAdapter {

    ArrayList<String> teamList;
    ArrayList<Team> teamList2;
    public TeamAdapter(ArrayList<String> list, ArrayList<Team> list2){
        this.teamList = list;
        this.teamList2 = list2;
    }

    @Override
    public int getCount() {
        return teamList2.size();
    }

    @Override
    public Object getItem(int i) {
        return teamList2.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ConstraintLayout layout;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        if(view == null){
            layout = (ConstraintLayout) inflater.inflate(R.layout.adapter_list_item, viewGroup, false);
        }else{
            layout = (ConstraintLayout) view;
        }
        Team T = teamList2.get(i);

        TextView tv2 = layout.findViewById(R.id.textView);
        tv2.setText(T.desc);

        TextView tv = layout.findViewById(R.id.nomEquipe);
        tv.setText(T.nom);

        ImageView iv = layout.findViewById(R.id.imageView);
        iv.setImageResource(T.imageID);

        return layout;
    }
}
