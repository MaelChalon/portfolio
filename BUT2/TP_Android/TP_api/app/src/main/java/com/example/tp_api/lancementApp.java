package com.example.tp_api;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.tp_api.Model.ListRecord;
import com.example.tp_api.Model.Reseaux;
import com.example.tp_api.manager.MainActivityController;
import com.example.tp_api.manager.ReseauxDataManagerCallBack;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

public class lancementApp extends AppCompatActivity implements ReseauxDataManagerCallBack {
    ListRecord listRecord;

    MainActivityController mainActivityController = new MainActivityController();
    private FusedLocationProviderClient fusedLocationClient;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;

    public static Location location;

    private static final long UPDATE_INTERVAL_IN_MILLISECONDS = 10000;
    private static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS = 5000;
    private NetworkInfo networkInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lancement_app);

        listRecord = ListRecord.getInstance();

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        locationRequest = new LocationRequest();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);

        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                for (Location loc : locationResult.getLocations()) {
                    location = loc;
                }
                getResaux();
            }
        };

        // Vérifier si l'application a la permission d'accéder à la localisation
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Si la permission n'est pas accordée, demander la permission
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        } else {
            // Si la permission est déjà accordée, lancer la demande de localisation
            startLocationUpdates();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // La permission a été accordée, lancer la demande de localisation
                startLocationUpdates();
            } else {
                // La permission n'a pas été accordée, utiliser une longitude et une latitude par défaut
                location = new Location("");
                location.setLatitude(46.205359);
                location.setLongitude(5.230288);
                getResaux();
            }
        }
    }

    private void startLocationUpdates() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper());
        }
    }

    private void stopLocationUpdates() {
        fusedLocationClient.removeLocationUpdates(locationCallback);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            startLocationUpdates();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopLocationUpdates();
    }

    @Override
    public void getReseauxResponseSuccess(Reseaux reseaux) {
        listRecord.push(reseaux.getRecords());
        Log.e("getReseauxResponseSuccess: ", listRecord.toString() );
        MainActivity.ligne+=20;
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void getReseauxResponseError(String message) {
        Log.e( "getReseauxResponseError: ", message );
    }

    private void getResaux(){
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            String loc = "" + lancementApp.location.getLatitude() + "," + lancementApp.location.getLongitude() + "," + "4500000";

            mainActivityController.getReseaux(this, Integer.toString(MainActivity.ligne), loc);
        } else {
            Toast.makeText(this, "aucun reseau disponnible", Toast.LENGTH_SHORT).show();
        }
    }
}

