package com.example.tp_api;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;

import com.example.tp_api.Model.Fields;
import com.example.tp_api.Model.Record;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class DetailAdapterItem extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    Record record;

    Fields fields;

    MapsActivity mapsActivity = new MapsActivity();


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_adapter_item);
        Intent intent = getIntent();
        if(intent.hasExtra("Record")){
            record = (Record) intent.getSerializableExtra("Record");
            fields = record.getFields();
        }

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragmentMap);
        mapFragment.getMapAsync(this);

        TextView tv = findViewById(R.id.nom_com);
        tv.setText(fields.getNomCom());

        tv = findViewById(R.id.nom_dep);
        tv.setText(fields.getNomDep());

        tv = findViewById(R.id.nom_reg);
        tv.setText(fields.getNomReg());

        tv = findViewById(R.id.insee_com);
        tv.setText(fields.getInseeCom());

        tv = findViewById(R.id.insee_dep);
        tv.setText(fields.getInseeDep());

        tv = findViewById(R.id.nom_op);
        tv.setText(fields.getNomOp());

        tv = findViewById(R.id.techno);
        tv.setText(fields.getTechnologies());

        tv = findViewById(R.id.longitude);
        tv.setText(Float.toString(fields.getCoordonnees().get(1)));

        tv = findViewById(R.id.latitude);
        tv.setText(Float.toString(fields.getCoordonnees().get(0)));
    }


    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

        mMap = googleMap;

        LatLng p = new LatLng(fields.getCoordonnees().get(0), fields.getCoordonnees().get(1));
        mMap.addMarker(new MarkerOptions().position(p).title(fields.getTechnologies()));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(p));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p, 15));

    }
}
