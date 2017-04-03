package com.android.example.shanghaiguide;


import android.content.Intent;
import android.location.LocationManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class DetailedActivity extends AppCompatActivity
        implements OnMapReadyCallback {
    //Logging TAG
    private static final String TAG = "DetailedActivity";
    private static final float CITY_LEVEL_ZOOM = 15.f;
    //Detailed place location
    private LatLng mDetailedLoc = null;
    private String mDetailedTitle = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle intentBundle = new Bundle(getIntent().getBundleExtra("location"));
        Log.v(TAG, "onCreate: Bundle: " + intentBundle.getDouble("latitude") + "/" +
                intentBundle.getDouble("longitude") + " Color: " + intentBundle.getInt("color"));

        Log.i(TAG, "Location: " +
                MainActivity.mlocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER).toString());


        // Find the color that the resource ID maps to
        int color = ContextCompat.getColor(this, intentBundle.getInt(getString(R.string.key_color)));

        // Get Detailed view ids
        TextView titleTextView = (TextView)findViewById(R.id.description_title_text);
        TextView descriptionTextView = (TextView)findViewById(R.id.description_full_text);
        TextView addressTextView = (TextView)findViewById(R.id.address_text);

        // Set the background color of the text container View
        titleTextView.setBackgroundColor(color);
        descriptionTextView.setBackgroundColor(color);
        addressTextView.setBackgroundColor(color);

        // Set Content of text views
        mDetailedTitle = intentBundle.getString(getString(R.string.key_title));
        titleTextView.setText(mDetailedTitle);
        descriptionTextView.setText(intentBundle.getString(getString(R.string.key_full_description)));
        addressTextView.setText(intentBundle.getString(getString(R.string.key_address)));


        // Get the MapFragment from layout, register callback
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //set the LatLng (google map object, latitude/longitude) from intent bundle
        mDetailedLoc = new LatLng(intentBundle.getDouble(getString(R.string.key_latitude)),
                                        intentBundle.getDouble(getString(R.string.key_longitude)));


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Add a marker at detailed location
        // and move the map's camera to the same location and zoom to city level
        googleMap.addMarker(new MarkerOptions().position(mDetailedLoc)
                .title(mDetailedTitle));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mDetailedLoc, CITY_LEVEL_ZOOM));
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                // TODO: this is not the right way, better to pass parent in intent bundle and call
                // TODO: NavUtils.navigateUpFromSameTask() - Remove switch statement
                // NavUtils.navigateUpFromSameTask(getIntent().getStringExtra("parent"));
                switch (getIntent().getStringExtra("parent")) {
                    case "SeeActivity":
                        Log.i(TAG, "Parent: SeeActivity");
                        startActivity(new Intent(this, SeeActivity.class));
                        return true;
                    case "EatActivity":
                        Log.i(TAG, "Parent: EatActvity");
                        startActivity(new Intent(this, EatActivity.class));
                        return true;
                }
                Log.e(TAG, "Parent: None");
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
