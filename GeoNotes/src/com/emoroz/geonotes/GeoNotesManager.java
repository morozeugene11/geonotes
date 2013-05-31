package com.emoroz.geonotes;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.emoroz.geonotes.entities.Geolocation;

public class GeoNotesManager {

	// DATABASE CONSTANTS
	private static final String DATABASE_NAME = "geonotes.sqlite";
	private static final int DATABASE_VERSION = 1;

	// TABLES NAMES
	private static final String TABLE_GEOLOCATIONS = "TABLE_GEOLOCATIONS";

	// GEOLOCATION TABLE COLUMNS
	private static final String COLUMN_GEOLOCATION_ID = "COLUMN_GEOLOCATION_ID";
	private static final String COLUMN_GEOLOCATION_NAME = "COLUMN_GEOLOCATION_NAME";
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

	public List<Geolocation> getGeolocation() {
		List<Geolocation> geolocations = new ArrayList<Geolocation>();
		Cursor cursor = database_.query(TABLE_GEOLOCATIONS, null, null, null,
				null, null, COLUMN_GEOLOCATION_NAME);
		for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
			long id = cursor.getLong(cursor
					.getColumnIndex(COLUMN_GEOLOCATION_ID));
			String name = cursor.getString(cursor
					.getColumnIndex(COLUMN_GEOLOCATION_NAME));
			double latitude = cursor.getDouble(cursor
					.getColumnIndex(COLUMN_GEOLOCATION_LATITUDE));
			double longitude = cursor.getDouble(cursor
					.getColumnIndex(COLUMN_GEOLOCATION_LONGITUDE));
			geolocations.add(new Geolocation(id, name, latitude, longitude));
		}
		return geolocations;
	}

	private ContentValues createContentValues(Geolocation geolocation) {
		ContentValues values = new ContentValues();
		values.put(COLUMN_GEOLOCATION_NAME, geolocation.getName());
		values.put(COLUMN_GEOLOCATION_LATITUDE, geolocation.getLatitude());
		values.put(COLUMN_GEOLOCATION_LONGITUDE, geolocation.getLongitude());
		return values;
	}

	public boolean addGeolocation(Geolocation geolocation) {
		return database_.insert(TABLE_GEOLOCATIONS, null,
				createContentValues(geolocation)) != -1;
	}

	public boolean updateGeolocation(Geolocation geolocation) {
		return database_.update(TABLE_GEOLOCATIONS,
				createContentValues(geolocation), COLUMN_GEOLOCATION_ID + "="
						+ geolocation.getId(), null) != 0;
	}

	public boolean removeGeolocation(long id) {
		return database_.delete(TABLE_GEOLOCATIONS, COLUMN_GEOLOCATION_ID + "="
				+ id, null) != 0;
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
