package com.psnapp.akbar.mygeolocation;

import android.Manifest;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {



    private static final String[] INITIAL_PERMS={
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.INTERNET,
            Manifest.permission.ACCESS_WIFI_STATE
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    try {

      GPSTracker _G=new GPSTracker(MainActivity.this);
        if ( _G.isGPSEnabled)
         Toast.makeText(MainActivity.this,"GPS Enable!",Toast.LENGTH_LONG).show();
        else
         Toast.makeText(MainActivity.this,"GPS Disable!",Toast.LENGTH_LONG).show();



        }catch (SecurityException ex)
        {ex.printStackTrace();}
    }




    }





