package com.android.example.shanghaiguide;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.android.example.shanghaiguide.PlacePersistenceContract.PlaceEntry;

import java.util.ArrayList;

public class EatActivity extends AppCompatActivity {

    //Logging TAG
    private static final String TAG = "EatActivity";
    private static final String ACTIVITY_NAME = "EatActivity";
    private static final String CATEGORY = "1";

    //Activity Color
    private final int mActivityColor = R.color.category_eat;
    //PlaceDbAssetHelper mDbHelper = new PlaceDbAssetHelper(this);
    private PlaceDbAssetHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place_list);
        Log.v(TAG, "onCreate:");
        Log.v(TAG, "Database Helper Test");
        mDbHelper = new PlaceDbAssetHelper(this);

        // Gets the data repository in write mode
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                PlaceEntry._ID,
                PlaceEntry.COLUMN_NAME_CATEGORY,
                PlaceEntry.COLUMN_NAME_DESCRIPTION_BRIEF,
        //      PlaceEntry.COLUMN_NAME_DESCRIPTION_DETAILED,
                PlaceEntry.COLUMN_NAME_IMAGE_THUMB_ID,
        //      PlaceEntry.COLUMN_NAME_IMAGE_ID,
                PlaceEntry.COLUMN_NAME_RATING,
                PlaceEntry.COLUMN_NAME_ADDRESS,
                PlaceEntry.COLUMN_NAME_LATITUDE,
                PlaceEntry.COLUMN_NAME_LONGITUDE
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = PlaceEntry.COLUMN_NAME_CATEGORY + " = ?";
        String[] selectionArgs = { CATEGORY };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                PlaceEntry.COLUMN_NAME_DESCRIPTION_BRIEF  + " DESC";

        Cursor cursor = db.query(
                PlaceEntry.TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );


        //Array list of places
        ArrayList<Place> places = new ArrayList<>();

        //Query DB for places data
        while(cursor.moveToNext()) {
            long itemId = cursor.getLong(
                    cursor.getColumnIndexOrThrow(PlaceEntry._ID));
            String db_descriptionBrief = cursor.getString(
                    cursor.getColumnIndexOrThrow(PlaceEntry.COLUMN_NAME_DESCRIPTION_BRIEF));
            //The R.drawable is an int returned by the R class, we can't store that in the DB, so
            //we store the R.drawable.NAME instead, and use getResources to return the INT for the
            //Image id associated with the R class. TODO: better way to do this?
            String db_image_thumb_id = cursor.getString(
                    cursor.getColumnIndexOrThrow(PlaceEntry.COLUMN_NAME_IMAGE_THUMB_ID));
            int imageResource = this.getResources().getIdentifier(db_image_thumb_id, "drawable", this.getPackageName());

            int db_rating = cursor.getInt(
                    cursor.getColumnIndexOrThrow(PlaceEntry.COLUMN_NAME_RATING));
            String db_address = cursor.getString(
                    cursor.getColumnIndexOrThrow(PlaceEntry.COLUMN_NAME_ADDRESS));
            double db_latitude = cursor.getDouble(
                    cursor.getColumnIndexOrThrow(PlaceEntry.COLUMN_NAME_LATITUDE));
            double db_longitude = cursor.getDouble(
                    cursor.getColumnIndexOrThrow(PlaceEntry.COLUMN_NAME_LONGITUDE));

            //Add places to array NOTE: imageResources is returned from db_image_thumb_id
            places.add(new Place(db_descriptionBrief, imageResource, db_rating,
                    db_address, db_latitude, db_longitude));
        }
        cursor.close();

        //Add place object to places arrayList
        /*
        places.add(new Place("Boxing Cat Brewery (Yongfu Lu)", R.drawable.boxing_cat_yongfu_thumb, 1,
                "复兴路82号, 近永福路", 31.2108309, 121.4408615));
        places.add(new Place("Drink UP Beer Convenience Store", R.drawable.drink_up_thumb, 2,
                "建国中路169号, 近瑞金二路", 31.2089731,121.4658453));
        places.add(new Place("Uptown Records n' Beer", R.drawable.up_town_beer_thumb, 3,
                "永福路131号, 近复兴路", 31.2099268,121.442875));
        */

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

    @Override
    protected void onDestroy() {
        mDbHelper.close();
        super.onDestroy();
    }
}
