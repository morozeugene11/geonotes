package com.emoroz.geonotes.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.emoroz.geonotes.R;
import com.emoroz.geonotes.entities.Geolocation;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class GeolocationInfoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ac_geolocation_info);

		TextView name = (TextView) findViewById(R.id.name);
		GoogleMap map = ((MapFragment) getFragmentManager().findFragmentById(
				R.id.map)).getMap();

		Geolocation geolocation = getIntent().getParcelableExtra(
				Geolocation.class.getCanonicalName());

		name.setText(geolocation.getName());
		LatLng latlng = new LatLng(geolocation.getLatitude(),
				geolocation.getLongitude());
		map.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng, 12));
		map.addMarker(new MarkerOptions().position(latlng));
	}
}
