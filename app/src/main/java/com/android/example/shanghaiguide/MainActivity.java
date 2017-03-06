package com.android.example.shanghaiguide;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //Logging TAG
    private static final String TAG = "MainActivity";

    //Location Permissions
    private static final String[] LOCATION_PERMS={
            Manifest.permission.ACCESS_FINE_LOCATION
    };

    //Initial request
    private static final int INITIAL_REQUEST=1337;

    // Acquire a reference to the system Location Manager
    public static LocationManager mlocationManager;

    // Define a listener that responds to location updates
    private final LocationListener mlocationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            // Called when a new location is found by the network location provider.
            Log.i(TAG,"Location: " + location.toString());
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {}

        public void onProviderEnabled(String provider) {}

        public void onProviderDisabled(String provider) {}
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED) {
            mlocationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
            // Register the listener with the Location Manager to receive location updates
            mlocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mlocationListener);
        } else {
            requestPermissions(LOCATION_PERMS, INITIAL_REQUEST);
        }


        //Set Places to see activity onClickListener
        TextView seeTextView = (TextView) findViewById(R.id.places_to_see);
        seeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity(view, SeeActivity.class);
            }
        });

        //Set Places to eat activity onClickListener
        TextView eatTextView = (TextView) findViewById(R.id.places_to_eat);
        eatTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity(view, EatActivity.class);
            }
        });
    }

    /**
     * Helper class to open an Activity Class
     * @param view not used - TODO:remove this?
     * @param cls a class to be opened
     */
    public void openActivity (View view, Class cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
        Log.i(TAG, "OpeningActivity: " + cls.getCanonicalName());
    }
}

