package com.example.listviewandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ArrayList<Team> listTeam2 = new ArrayList<Team>();

    ArrayList<String> listTeam = new ArrayList<String>(Arrays.asList(
            "test",
            "OM",
            "OL",
            "PSG",
            "wowowow",
            "pourquoi pas"
    ));


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTeam();

        TeamAdapter adapter = new TeamAdapter(listTeam, listTeam2);
        ListView lv = findViewById(R.id.ListView);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, listTeam2.get(i).nom, Toast.LENGTH_SHORT).show();
            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                listTeam2.remove(i);
                adapter.notifyDataSetChanged();
                return true;
            }
        });
    }

    private void initTeam(){
        for(String name : listTeam){
            Team T = new Team(name, randomImage());
            listTeam2.add(T);
        }
    }

    private int randomImage(){
        int i = new Random().nextInt(3) + 1;
        String name = "fanion" + i;
        int retour = this.getResources().getIdentifier(name, "drawable", getPackageName());
        return retour;
    }

}