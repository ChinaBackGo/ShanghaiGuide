package com.android.example.shanghaiguide;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class EatActivity extends AppCompatActivity {

    //Logging TAG
    private static final String TAG = "EatActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place_list);
        Log.v(TAG, "onCreate:");

        //Array list of places
        ArrayList<Place> places = new ArrayList<>();


        //Add place object to places arrayList
        places.add(new Place("Description1", R.drawable.ic_ac_unit_black_24dp, 1, -1));
        places.add(new Place("Description2", R.drawable.ic_ac_unit_black_24dp, 2, -1));
        places.add(new Place("Description3", R.drawable.ic_ac_unit_black_24dp, 3, -1));

        // Create an {@link PlaceAdapter}, whose data source is a list of place objects. The
        // adapter knows how to create layouts for each item in the list, using the
        // getView override method.
        // This list item layout from list_item layout
        PlaceAdapter adapter =
                new PlaceAdapter(this, places, R.color.category_eat);

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


        // TODO: Register onClick listener to show detailed view
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               // Log.v(TAG, "onItemClick: " + currentWord.toString());
            }
        });
    }
}
