package com.emoroz.geonotes.activities;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;

import com.emoroz.geonotes.R;
import com.emoroz.geonotes.adapters.GeolocationAdapter;
import com.emoroz.geonotes.entities.Geolocation;

public class GeolocationListActivity extends Activity {

	private GeolocationAdapter adapter_;
	private List<Geolocation> geolocations_ = new ArrayList<Geolocation>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ac_geolocation_list);

		ListView listView = (ListView) findViewById(R.id.list);
		adapter_ = new GeolocationAdapter(this, geolocations_);
		listView.setAdapter(adapter_);
		listView.setOnScrollListener(new OnScrollListener() {
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				adapter_.setAnimate(scrollState == SCROLL_STATE_FLING
						|| SCROLL_STATE_TOUCH_SCROLL == scrollState);
			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
			}
		});

		loadGeolocation();
	}

	private void loadGeolocation() {
		geolocations_.clear();
		for (int i = 0; i < 100; i++) {
			geolocations_.add(new Geolocation("GEO " + i, R.drawable.ic_geo_diner, 0, 0));
		}
		adapter_.notifyDataSetChanged();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.options, menu);
		return true;
	}

}
