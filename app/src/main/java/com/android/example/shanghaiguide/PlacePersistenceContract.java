package com.android.example.shanghaiguide;

import android.provider.BaseColumns;

/**
 * The contract used for the db to save the tasks locally.
 */

public class PlacePersistenceContract {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private PlacePersistenceContract() {}

    /* Inner class that defines the table contents */
    public static abstract class PlaceEntry implements BaseColumns {
        public static final String TABLE_NAME = "places";
        public static final String COLUMN_NAME_DESCRIPTION_BRIEF = "description_brief";
        public static final String COLUMN_NAME_DESCRIPTION_DETAILED = "description_detailed";
        public static final String COLUMN_NAME_IMAGE_THUMB_ID = "image_thumb_id";
        public static final String COLUMN_NAME_IMAGE_ID = "image_id";
        public static final String COLUMN_NAME_RATING = "rating";
        public static final String COLUMN_NAME_ADDRESS = "address";
        public static final String COLUMN_NAME_LATITUDE = "latitude";
        public static final String COLUMN_NAME_LONGITUDE = "longitude";
    }
}