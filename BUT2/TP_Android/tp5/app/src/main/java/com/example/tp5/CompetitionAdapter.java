package com.example.tp5;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.tp5.model.Competition;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CompetitionAdapter extends BaseAdapter {

    ArrayList<Competition> listeCompetition = null;

    public CompetitionAdapter(ArrayList<Competition> list){
        listeCompetition = list;
    }

    @Override
    public int getCount() {
        return listeCompetition.size();
    }

    @Override
    public Object getItem(int position) {
        return listeCompetition.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ConstraintLayout layout;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        if(convertView == null){
            layout = (ConstraintLayout) inflater.inflate(R.layout.adaptater_competitoin_list_item, parent, false);
        }else{
            layout = (ConstraintLayout) convertView;
        }

        Competition c = listeCompetition.get(position);

        ImageView imageView = layout.findViewById(R.id.imgCompetition);
        imageView.setImageResource(R.drawable.ic_launcher_background);
        if(!c.getLeagueLogo().isEmpty()){
            Picasso.get().load(c.getLeagueLogo()).into(imageView);
        }


        TextView tv = layout.findViewById(R.id.textViewLeagueId);
        tv.setText(c.getLeagueId());

        tv = layout.findViewById(R.id.textViewLeagueName);
        tv.setText(c.getLeagueName());

        tv = layout.findViewById(R.id.textViewLeagueSeason);
        tv.setText(c.getLeagueSeason());

        return layout;
    }

    public void setListeCompetition(ArrayList<Competition> listeCompetition) {
        this.listeCompetition = listeCompetition;
    }
}
