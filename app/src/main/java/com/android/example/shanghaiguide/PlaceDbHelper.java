package com.android.example.shanghaiguide;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * DB Helper see:
 * https://developer.android.com/guide/topics/data/data-storage.html#db
 * https://developer.android.com/training/basics/data-storage/databases.html#DbHelper
 * https://github.com/googlesamples/android-architecture/tree/todo-mvp/todoapp/app/src/main/java/
 * com/example/android/architecture/blueprints/todoapp/data/source/local
 */

public class PlaceDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ShanghaiGuide.db";

    //SQLite DBHelper SQL string
    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String FLOAT_TYPE = " REAL";
    private static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + PlacePersistenceContract.PlaceEntry.TABLE_NAME + " (" +
                    PlacePersistenceContract.PlaceEntry._ID + INTEGER_TYPE + " PRIMARY KEY," +
                    PlacePersistenceContract.PlaceEntry.COLUMN_NAME_DESCRIPTION_BRIEF + TEXT_TYPE + COMMA_SEP +
                    PlacePersistenceContract.PlaceEntry.COLUMN_NAME_DESCRIPTION_DETAILED + TEXT_TYPE + COMMA_SEP +
                    PlacePersistenceContract.PlaceEntry.COLUMN_NAME_IMAGE_THUMB_ID + INTEGER_TYPE + COMMA_SEP +
                    PlacePersistenceContract.PlaceEntry.COLUMN_NAME_IMAGE_ID + INTEGER_TYPE + COMMA_SEP +
                    PlacePersistenceContract.PlaceEntry.COLUMN_NAME_RATING + INTEGER_TYPE + COMMA_SEP +
                    PlacePersistenceContract.PlaceEntry.COLUMN_NAME_ADDRESS + TEXT_TYPE + COMMA_SEP +
                    PlacePersistenceContract.PlaceEntry.COLUMN_NAME_LATITUDE + FLOAT_TYPE + COMMA_SEP +
                    PlacePersistenceContract.PlaceEntry.COLUMN_NAME_LONGITUDE + FLOAT_TYPE +
                    " )";

    public PlaceDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }
}
