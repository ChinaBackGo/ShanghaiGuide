package com.android.example.shanghaiguide;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NavUtils;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DetailedActivity extends AppCompatActivity {
    //Logging TAG
    private static final String TAG = "DetailedActivity";

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
        int color = ContextCompat.getColor(this, intentBundle.getInt("color"));

        // Get Detailed view ids
        TextView titleTextView = (TextView)findViewById(R.id.description_title_text);
        TextView descriptionTextView = (TextView)findViewById(R.id.description_full_text);
        TextView addressTextView = (TextView)findViewById(R.id.address_text);

        // Set the background color of the text container View
        titleTextView.setBackgroundColor(color);
        descriptionTextView.setBackgroundColor(color);
        addressTextView.setBackgroundColor(color);

        // Set Content of text views
        titleTextView.setText(intentBundle.getString("title"));
        descriptionTextView.setText(intentBundle.getString("full_text"));
        addressTextView.setText(intentBundle.getString("address"));


        // Todo: Feature- MapView
        ImageView mapImageView = (ImageView)findViewById(R.id.map_view);
        mapImageView.setImageResource(R.drawable.ic_ac_unit_black_24dp);

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
