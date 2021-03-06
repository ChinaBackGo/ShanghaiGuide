package com.android.example.shanghaiguide;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class SeeActivity extends AppCompatActivity {

    //Logging TAG
    private static final String TAG = "SeeActivity";
    //Activity name
    private static final String ACTIVITY_NAME = "SeeActivity";
    //Category DB id
    private static final String CATEGORY = "2";

    //Activity Color
    private final int mActivityColor = R.color.category_see;
    //asset helper object
    private PlaceDbAssetHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place_list);
        Log.v(TAG, "onCreate:");

        // Get the database
        mDbHelper = new PlaceDbAssetHelper(this);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        //Array list of places from db
        ArrayList<Place> places = mDbHelper.getArrayOfPlaces(db, this, CATEGORY);


        // Create an {@link PlaceAdapter}, whose data source is a list of place objects. The
        // adapter knows how to create layouts for each item in the list, using the
        // getView override method.
        // This list item layout from list_item layout
        PlaceAdapter adapter =
                new PlaceAdapter(this, places, mActivityColor);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // place_list.xml file.
        ListView listView = (ListView) findViewById(R.id.list);

        // Make the {@link ListView} use the {@link ArrayAdapter} overidded by PlaceAdapter we
        // created above, so that the {@link ListView} will display list items for each word in the
        // list of words.
        // Do this by calling the setAdapter method on the {@link ListView} object and pass in
        // 1 argument, which is the {@link ArrayAdapter} with the variable name adapter.
        listView.setAdapter(adapter);


        // Register onClick listener to show detailed view
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // parent here is the view that was clicked on in the listView
                // getItemAtPosition returns the Place object associated with that view
                Place currentPlace = (Place) parent.getItemAtPosition(position);
                Log.v(TAG, "onItemClick: " + currentPlace.toString());

                // Get the Place data (and category color) and create a bundle
                Bundle localBundle = new Bundle();
                localBundle.putDouble(getString(R.string.key_latitude), currentPlace.getLatitude());
                localBundle.putDouble(getString(R.string.key_longitude), currentPlace.getLongitude());
                localBundle.putInt(getString(R.string.key_color), mActivityColor);
                localBundle.putString(getString(R.string.key_title), currentPlace.getPlaceDescriptionBrief());
                localBundle.putString(getString(R.string.key_address), currentPlace.getAddress());
                localBundle.putString(getString(R.string.key_full_description), currentPlace.getPlaceDescriptionDetailed());

                Log.v(TAG, "onItemClick: locationBundle " + localBundle.toString());

                // Put the bundle in the intent
                Intent intent = new Intent(view.getContext(), DetailedActivity.class);
                intent.putExtra(getString(R.string.key_location), localBundle);
                // Add calling intent class
                intent.putExtra(getString(R.string.key_parent), ACTIVITY_NAME);
                startActivity(intent);
            }
        });
    }
}