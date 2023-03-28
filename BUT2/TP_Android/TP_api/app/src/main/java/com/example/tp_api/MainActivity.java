package com.example.tp_api;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.tp_api.Adapter.ReseauAdapter;
import com.example.tp_api.Model.ListRecord;
import com.example.tp_api.Model.Record;
import com.example.tp_api.Model.Reseaux;
import com.example.tp_api.manager.MainActivityController;
import com.example.tp_api.manager.ReseauxDataManagerCallBack;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements ReseauxDataManagerCallBack {

    public static int ligne = 0;
    ListRecord listRecord;
    MainActivityController mainActivityController = new MainActivityController();

    ReseauAdapter adapter;

    NetworkInfo networkInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listRecord = listRecord.getInstance();
        adapter = new ReseauAdapter(listRecord.getListRecord());

        FloatingActionButton map = findViewById(R.id.buttonMap);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchCarteList();
            }
        });

        ListView lv = findViewById(R.id.listViewMain);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Record affiche = listRecord.getRecord(i);
                switchCartePoint(affiche);
            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Record r;
                ImageView iv = view.findViewById(R.id.favorite);

                if(listRecord.afficheFavorite()){
                    r = listRecord.getRecordFavorite(position);
                    iv.setImageDrawable(iv.getResources().getDrawable(R.drawable.baseline_favorite_24));
                    listRecord.remooveFavorite(r);
                    adapter.notifyDataSetChanged();
                }else{
                    r = listRecord.getRecord(position);
                    if(listRecord.isFavorite(r)){
                        iv.setImageDrawable(iv.getResources().getDrawable(R.drawable.baseline_favorite_24));
                        listRecord.remooveFavorite(r);
                        adapter.notifyDataSetChanged();
                    }else{
                        iv.setImageDrawable(iv.getResources().getDrawable(R.drawable.baseline_favorite_25));
                        listRecord.addFavorite(r);
                        adapter.notifyDataSetChanged();
                    }
                }


                return true;
            }
        });

        lv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {}

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if(!listRecord.afficheFavorite()){
                    if (firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount != 0) {
                        getReseaux();
                    }
                }

            }
        });

        FloatingActionButton buttonFavorite = findViewById(R.id.buttonFavorite);
        buttonFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listRecord.afficheFavorite()){
                    listRecord.changeFavorite();
                    adapter.notifyDataSetChanged();
                }
                else{
                    if(listRecord.getListRecordFavorite().size() != 0) {
                        listRecord.changeFavorite();
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        });

        getReseaux();
    }

    private void getReseaux(){
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            String loc = "" + lancementApp.location.getLatitude() + "," + lancementApp.location.getLongitude() + "," + "4500000";

            mainActivityController.getReseaux(this, Integer.toString(ligne), loc);
        } else {
            Toast.makeText(this, "aucun reseau disponnible", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getReseauxResponseSuccess(Reseaux reseaux) {
        listRecord.push(reseaux.getRecords());
        adapter.notifyDataSetChanged();
        Log.e("getReseauxResponseSuccess: ", listRecord.toString() );
        ligne+=20;
    }

    @Override
    public void getReseauxResponseError(String message) {
        Log.e( "getReseauxResponseError: ", message );
    }

    public void switchCartePoint(Record r){
        Intent i = new Intent(getApplicationContext(), DetailAdapterItem.class);
        i.putExtra("Record", r);
        startActivity(i);
    }

    public void switchCarteList(){
        Intent i = new Intent(getApplicationContext(), MapsActivity.class);
        startActivity(i);
    }
}
