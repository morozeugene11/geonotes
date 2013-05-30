package com.emoroz.geonotes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GeoNotesManager {

	// DATABASE CONSTANTS
	private static final String DATABASE_NAME = "geonotes.sqlite";
	private static final int DATABASE_VERSION = 1;

	// TABLES NAMES
	private static final String TABLE_GEOLOCATIONS = "TABLE_GEOLOCATIONS";

	// GEOLOCATION TABLE COLUMNS
	private static final String COLUMN_GEOLOCATION_ID = "COLUMN_GEOLOCATION_ID";
	private static final String COLUMN_GEOLOCATION_NAME = "COLUMN_GEOLOCATION_NAME";
	private static final String COLUMN_GEOLOCATION_DRAWABLE_ID = "COLUMN_GEOLOCATION_DRAWABLE_ID";
	private static final String COLUMN_GEOLOCATION_LATITUDE = "COLUMN_GEOLOCATION_LATITUDE";
	private static final String COLUMN_GEOLOCATION_LONGITUDE = "COLUMN_GEOLOCATION_LONGITUDE";

	// CREATE TABLES REQUESTS
	private static final String CREATE_TABLE_GEOLOCATIONS = "CREATE TABLE IF NOT EXISTS "
			+ TABLE_GEOLOCATIONS
			+ "("
			+ COLUMN_GEOLOCATION_ID
			+ " INTEGER PRIMARY KEY, "
			+ COLUMN_GEOLOCATION_NAME
			+ " TEXT NOT NULL, "
			+ COLUMN_GEOLOCATION_DRAWABLE_ID
			+ " INTEGER NOT NULL, "
			+ COLUMN_GEOLOCATION_LATITUDE
			+ " REAL NOT NULL, "
			+ COLUMN_GEOLOCATION_LONGITUDE
			+ " REAL NOT NULL);";

	private static GeoNotesManager instance_;
	private DatabaseHelper helper_;
	private SQLiteDatabase database_;

	public static GeoNotesManager getInstance(Context context) {
		if (instance_ == null) {
			instance_ = new GeoNotesManager(context);
		}
		return instance_;
	}

	private GeoNotesManager(Context context) {
		helper_ = new DatabaseHelper(context);
		database_ = helper_.getWritableDatabase();
	}

	private class DatabaseHelper extends SQLiteOpenHelper {

		public DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			try {
				db.beginTransaction();
				db.execSQL(CREATE_TABLE_GEOLOCATIONS);
				db.setTransactionSuccessful();
			} finally {
				db.endTransaction();
			}
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		}

	}

}
