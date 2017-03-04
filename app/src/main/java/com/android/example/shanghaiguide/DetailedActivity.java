package com.android.example.shanghaiguide;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

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
                intentBundle.getDouble("longitude"));

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
