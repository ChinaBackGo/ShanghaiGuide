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
        places.add(new Place("Boxing Cat Brewery (Yongfu Lu)", R.drawable.boxing_cat_yongfu_thumb, 1,
                "复兴路82号, 近永福路", 31.2108309, 121.4408615));
        places.add(new Place("Drink UP Beer Convenience Store", R.drawable.drink_up_thumb, 2,
                "建国中路169号, 近瑞金二路", 31.2089731,121.4658453));
        places.add(new Place("Uptown Records n' Beer", R.drawable.up_town_beer_thumb, 3,
                "永福路131号, 近复兴路", 31.2099268,121.442875));

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
                Place currentPlace = (Place) parent.getItemAtPosition(position);
                Log.v(TAG, "onItemClick: " + currentPlace.toString());
            }
        });
    }
}
