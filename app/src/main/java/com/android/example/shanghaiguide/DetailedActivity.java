package com.android.example.shanghaiguide;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class DetailedActivity extends AppCompatActivity {
    //Logging TAG
    private static final String TAG = "DetailedActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        Bundle intentBundle = new Bundle(getIntent().getBundleExtra("location"));
        Log.v(TAG, "onCreate: Bundle: " + intentBundle.getDouble("latitude") + "/" +
                intentBundle.getDouble("longitude"));

    }
}
