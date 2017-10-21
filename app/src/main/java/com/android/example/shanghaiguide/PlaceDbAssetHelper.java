package com.android.example.shanghaiguide;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;

public class PlaceDbAssetHelper extends SQLiteAssetHelper {

	private static final String DATABASE_NAME = "ShanghaiGuide.db";
	private static final int DATABASE_VERSION = 1;

	public PlaceDbAssetHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		
		// you can use an alternate constructor to specify a database location 
		// (such as a folder on the sd card)
		// you must ensure that this folder is available and you have permission
		// to write to it
		//super(context, DATABASE_NAME, context.getExternalFilesDir(null).getAbsolutePath(), null, DATABASE_VERSION);
		
	}

	public ArrayList getArrayOfPlaces(SQLiteDatabase db, Context context, String category) {

		//Check if db null
		if (db == null) {
			throw new IllegalStateException("db is null");
		};

		// Define a projection that specifies which columns from the database
		// you will actually use after this query.
		String[] projection = {
				PlacePersistenceContract.PlaceEntry._ID,
				PlacePersistenceContract.PlaceEntry.COLUMN_NAME_CATEGORY,
				PlacePersistenceContract.PlaceEntry.COLUMN_NAME_DESCRIPTION_BRIEF,
				//      PlaceEntry.COLUMN_NAME_DESCRIPTION_DETAILED,
				PlacePersistenceContract.PlaceEntry.COLUMN_NAME_IMAGE_THUMB_ID,
				//      PlaceEntry.COLUMN_NAME_IMAGE_ID,
				PlacePersistenceContract.PlaceEntry.COLUMN_NAME_RATING,
				PlacePersistenceContract.PlaceEntry.COLUMN_NAME_ADDRESS,
				PlacePersistenceContract.PlaceEntry.COLUMN_NAME_LATITUDE,
				PlacePersistenceContract.PlaceEntry.COLUMN_NAME_LONGITUDE
		};

		// Filter results WHERE "title" = 'My Title'
		String selection = PlacePersistenceContract.PlaceEntry.COLUMN_NAME_CATEGORY + " = ?";
		String[] selectionArgs = { category };

		// How you want the results sorted in the resulting Cursor
		String sortOrder =
				PlacePersistenceContract.PlaceEntry.COLUMN_NAME_DESCRIPTION_BRIEF  + " DESC";

		Cursor cursor = db.query(
				PlacePersistenceContract.PlaceEntry.TABLE_NAME,                     // The table to query
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
					cursor.getColumnIndexOrThrow(PlacePersistenceContract.PlaceEntry._ID));
			String db_descriptionBrief = cursor.getString(
					cursor.getColumnIndexOrThrow(PlacePersistenceContract.PlaceEntry.COLUMN_NAME_DESCRIPTION_BRIEF));
			//The R.drawable is an int returned by the R class, we can't store that in the DB, so
			//we store the R.drawable.NAME instead, and use getResources to return the INT for the
			//Image id associated with the R class. TODO: better way to do this?
			String db_image_thumb_id = cursor.getString(
					cursor.getColumnIndexOrThrow(PlacePersistenceContract.PlaceEntry.COLUMN_NAME_IMAGE_THUMB_ID));
			int imageResource = context.getResources().getIdentifier(db_image_thumb_id, "drawable", context.getPackageName());

			int db_rating = cursor.getInt(
					cursor.getColumnIndexOrThrow(PlacePersistenceContract.PlaceEntry.COLUMN_NAME_RATING));
			String db_address = cursor.getString(
					cursor.getColumnIndexOrThrow(PlacePersistenceContract.PlaceEntry.COLUMN_NAME_ADDRESS));
			double db_latitude = cursor.getDouble(
					cursor.getColumnIndexOrThrow(PlacePersistenceContract.PlaceEntry.COLUMN_NAME_LATITUDE));
			double db_longitude = cursor.getDouble(
					cursor.getColumnIndexOrThrow(PlacePersistenceContract.PlaceEntry.COLUMN_NAME_LONGITUDE));

			//Add places to array NOTE: imageResources is returned from db_image_thumb_id
			places.add(new Place(db_descriptionBrief, imageResource, db_rating,
					db_address, db_latitude, db_longitude));
		}
		cursor.close();
		return places;
	}
}
