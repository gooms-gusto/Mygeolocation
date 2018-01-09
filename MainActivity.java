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
    EditText _TxtAccuracy;
    EditText _txtProvider;
    GoogleApiClient mGoogleApiClient;
    private static final String TAG = "LocationActivity";
    private static final long INTERVAL = 1000 * 10;
    private static final long FASTEST_INTERVAL = 1000 * 5;
    String mLastUpdateTime;
    Location mCurrentLocation;
    LocationRequest mLocationRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        _BtnProcess=(Button)findViewById(R.id._BtnSubmit);
        _TxtLattitude=(EditText)findViewById(R.id._input_lattitude);
        _TxtLongittude=(EditText) findViewById(R.id._input_longittude);
        _TxtAccuracy=(EditText) findViewById(R.id._input_accuracy);
        _txtProvider=(EditText) findViewById(R.id._input_provider);


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

        Log.d(TAG, "onStart fired ..............");
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.d(TAG, "onStop fired ..............");
        mGoogleApiClient.disconnect();
        Log.d(TAG, "isConnected ...............: " + mGoogleApiClient.isConnected());
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.d(TAG, "onConnected - isConnected ...............: " + mGoogleApiClient.isConnected());
        startLocationUpdates();
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

    @Override
    protected void onResume() {
        super.onResume();

        if (mGoogleApiClient.isConnected()) {
            startLocationUpdates();
            Log.d(TAG, "Location update resumed .....................");
        }
    }

    private void updateUI() {
        Log.d(TAG, "UI update initiated .............");
        if (null != mCurrentLocation) {
            String lat = String.valueOf(mCurrentLocation.getLatitude());
            String lng = String.valueOf(mCurrentLocation.getLongitude());
            _TxtLattitude.setText(lat);
            _TxtLongittude.setText(lng);
            _TxtAccuracy.setText(String.valueOf(mCurrentLocation.getAccuracy()));
            _txtProvider.setText(mCurrentLocation.getProvider());


        } else {
            Log.d(TAG, "location is null ...............");
        }
    }

    private boolean isGooglePlayServicesAvailable() {
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (ConnectionResult.SUCCESS == status) {
            return true;
        } else {
            GooglePlayServicesUtil.getErrorDialog(status, this, 0).show();
            return false;
        }
    }


    protected void startLocationUpdates() {
        try {
            PendingResult<Status> pendingResult = LocationServices.FusedLocationApi.requestLocationUpdates(
                    mGoogleApiClient, mLocationRequest, this);
            Log.d(TAG, "Location update started ..............: ");
        }
        catch (SecurityException ex){
            ex.printStackTrace();
        }
    }

    protected void stopLocationUpdates() {
        LocationServices.FusedLocationApi.removeLocationUpdates(
                mGoogleApiClient, this);
        Log.d(TAG, "Location update stopped .......................");
    }

}





