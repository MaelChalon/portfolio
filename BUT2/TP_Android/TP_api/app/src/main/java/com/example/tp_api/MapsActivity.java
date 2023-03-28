package com.example.tp_api;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.tp_api.Model.Fields;
import com.example.tp_api.Model.ListRecord;
import com.example.tp_api.Model.Record;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.tp_api.databinding.ActivityMapsBinding;
import com.google.maps.android.clustering.ClusterManager;

import java.util.ArrayList;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    private ListRecord listRecord;

    ClusterManager<Record> clusterManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listRecord = listRecord.getInstance();

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        clusterManager=new ClusterManager<>(getApplicationContext(), mMap);

        createCluster();

        LatLng loc = new LatLng(lancementApp.location.getLatitude(), lancementApp.location.getLongitude());
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, 12));
    }

    private void createCluster(){
        ArrayList<Record> list;
        if(listRecord.afficheFavorite()){
            list = listRecord.getListRecordFavorite();
        }else{
            list = listRecord.getListRecord();
        }

        mMap.clear();
        clusterManager.clearItems();
        for (Record record: list) {
            clusterManager.addItem(record);
        }

        clusterManager.setOnClusterItemInfoWindowClickListener(new ClusterManager.OnClusterItemInfoWindowClickListener<Record>() {
            @Override
            public void onClusterItemInfoWindowClick(Record item) {
                Intent intent = new Intent(getApplicationContext(), DetailAdapterItem.class);
                intent.putExtra("Record",item);
                startActivity(intent);
            }
        });

        mMap.setOnMarkerClickListener(clusterManager);
        mMap.setOnCameraIdleListener(clusterManager);

    }
}

