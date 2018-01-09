package com.psnapp.akbar.mygeolocation;

import android.Manifest;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import java.text.DateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements  LocationListener,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {



    private static final String[] INITIAL_PERMS={
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.INTERNET,
            Manifest.permission.ACCESS_WIFI_STATE,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };


    Button _BtnProcess;
    EditText _TxtLattitude;
    EditText _TxtLongittude;
    private static final String TAG = "LocationActivity";
    private static final long INTERVAL = 1000 * 10;
    private static final long FASTEST_INTERVAL = 1000 * 5;
    String mLastUpdateTime;
    Location mCurrentLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        _BtnProcess=(Button)findViewById(R.id._BtnSubmit);
        _TxtLattitude=(EditText)findViewById(R.id._input_lattitude);
        _TxtLongittude=(EditText) findViewById(R.id._input_longittude);


        _BtnProcess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

    try {

      GPSTracker _G=new GPSTracker(MainActivity.this);
        if ( _G.isGPSEnabled)
        {
           _G.getLocation();
           double _tes= _G.getLatitude();
            Toast.makeText(MainActivity.this,String.valueOf(_tes),Toast.LENGTH_LONG).show();
        }
        else
         Toast.makeText(MainActivity.this,"GPS Disable!",Toast.LENGTH_LONG).show();



        }catch (SecurityException ex)
        {ex.printStackTrace();}
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {

    }


    private void updateUI() {
        Log.d(TAG, "UI update initiated .............");
        if (null != mCurrentLocation) {
            String lat = String.valueOf(mCurrentLocation.getLatitude());
            String lng = String.valueOf(mCurrentLocation.getLongitude());
            _TxtLattitude.setText(lat);
            _TxtLongittude.setText(lng);


            tvLocation.setText("At Time: " + mLastUpdateTime + "\n" +
                    "Latitude: " + lat + "\n" +
                    "Longitude: " + lng + "\n" +
                    "Accuracy: " + mCurrentLocation.getAccuracy() + "\n" +
                    "Provider: " + mCurrentLocation.getProvider());
        } else {
            Log.d(TAG, "location is null ...............");
        }
    }
}





