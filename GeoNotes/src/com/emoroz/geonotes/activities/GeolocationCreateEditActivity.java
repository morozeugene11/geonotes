package com.emoroz.geonotes.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.emoroz.geonotes.R;
import com.emoroz.geonotes.entities.Geolocation;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class GeolocationCreateEditActivity extends Activity {

	private EditText name_;
	private GoogleMap map_;

	private Marker marker_;
	private Geolocation geolocation_;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ac_geolocation_create_edit);

		name_ = (EditText) findViewById(R.id.name);
		map_ = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
				.getMap();
		map_.setOnMapClickListener(new OnMapClickListener() {
			@Override
			public void onMapClick(LatLng coords) {
				map_.clear();
				marker_ = map_.addMarker(new MarkerOptions().position(coords));
			}
		});

		if (getIntent().hasExtra(Geolocation.class.getCanonicalName())) {
			geolocation_ = (Geolocation) getIntent().getParcelableExtra(
					Geolocation.class.getCanonicalName());
			name_.setText(geolocation_.getName());
			LatLng latlng = new LatLng(geolocation_.getLatitude(),
					geolocation_.getLongitude());
			marker_ = map_.addMarker(new MarkerOptions().position(latlng));
			map_.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng, 12));
		} else {
			geolocation_ = new Geolocation();
		}
	}

	public void onOkClicked(View view) {
		if (name_.length() == 0) {
			Toast.makeText(this, "Enter name", Toast.LENGTH_LONG).show();
			return;
		}
		if (marker_ == null) {
			Toast.makeText(this, "Choose coordinates", Toast.LENGTH_LONG)
					.show();
			return;
		}
		geolocation_.setName(name_.getText().toString());
		geolocation_.setLatitude(marker_.getPosition().latitude);
		geolocation_.setLongitude(marker_.getPosition().longitude);
		setResult(RESULT_OK, new Intent().putExtra(
				Geolocation.class.getCanonicalName(), geolocation_));
		finish();
	}

}
