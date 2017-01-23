package com.android.example.shanghaiguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //Logging TAG
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

