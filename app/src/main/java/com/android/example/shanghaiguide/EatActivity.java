package com.android.example.shanghaiguide;

import android.content.Context;
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

        //String[] words = new String[11];
        //TODO: Refactor
        //ArrayList<Place> Places = new ArrayList<>();


        //Add word object to words arrayList
        //TODO: Refactor for places
        //words.add(new Word("red", "weṭeṭṭi", R.drawable.color_red, R.raw.color_red));

        // Create an {@link WordAdapter}, whose data source is a list of Word objects. The
        // adapter knows how to create layouts for each item in the list, using the
        // getView overrided method.
        // This list item layout two text views and an image resource
        //TODO: Refactor
        //PlacesAdapter adapter =
        //        new PlacesAdapter(this, words);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // word_list.xml file.
        ListView listView = (ListView) findViewById(R.id.list);

        // Make the {@link ListView} use the {@link ArrayAdapter} we created above, so that the
        // {@link ListView} will display list items for each word in the list of words.
        // Do this by calling the setAdapter method on the {@link ListView} object and pass in
        // 1 argument, which is the {@link ArrayAdapter} with the variable name itemsAdapter.
        //TODO Refactor
        //listView.setAdapter(adapter);


        //Register onClick listerner to start audio playback of word
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               // Log.v(TAG, "onItemClick: " + currentWord.toString());
            }
        });
    }
}
