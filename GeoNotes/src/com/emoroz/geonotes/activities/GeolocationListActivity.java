package com.emoroz.geonotes.activities;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.emoroz.geonotes.GeoNotesManager;
import com.emoroz.geonotes.R;
import com.emoroz.geonotes.adapters.GeolocationAdapter;
import com.emoroz.geonotes.entities.Geolocation;

public class GeolocationListActivity extends Activity {

	private static final int REQUEST_CREATE_GEOLOCATION = 0;
	private static final int REQUEST_EDIT_GEOLOCATION = 1;
	private GeolocationAdapter adapter_;
	private List<Geolocation> geolocations_ = new ArrayList<Geolocation>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ac_geolocation_list);

		ListView listView = (ListView) findViewById(R.id.list);
		adapter_ = new GeolocationAdapter(this, geolocations_);
		listView.setAdapter(adapter_);
		registerForContextMenu(listView);
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
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent = new Intent(GeolocationListActivity.this,
						GeolocationInfoActivity.class);
				intent.putExtra(Geolocation.class.getCanonicalName(),
						geolocations_.get(arg2));
				startActivity(intent);
			}
		});

		loadGeolocation();
	}

	private void loadGeolocation() {
		geolocations_.clear();
		geolocations_
				.addAll(GeoNotesManager.getInstance(this).getGeolocation());
		adapter_.notifyDataSetChanged();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.options, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_add:
			startActivityForResult(new Intent(this,
					GeolocationCreateEditActivity.class),
					REQUEST_CREATE_GEOLOCATION);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		getMenuInflater().inflate(R.menu.context, menu);
		super.onCreateContextMenu(menu, v, menuInfo);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
				.getMenuInfo();
		switch (item.getItemId()) {
		case R.id.menu_edit:
			startActivityForResult(new Intent(this,
					GeolocationCreateEditActivity.class).putExtra(
					Geolocation.class.getCanonicalName(),
					geolocations_.get(info.position)), REQUEST_EDIT_GEOLOCATION);
			return true;
		case R.id.menu_remove:
			GeoNotesManager.getInstance(this).removeGeolocation(
					geolocations_.get(info.position).getId());
			loadGeolocation();
			return true;
		default:
			return super.onContextItemSelected(item);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQUEST_CREATE_GEOLOCATION
				|| REQUEST_EDIT_GEOLOCATION == requestCode) {
			if (resultCode != RESULT_OK) {
				return;
			}
			Geolocation geolocation = (Geolocation) data
					.getParcelableExtra(Geolocation.class.getCanonicalName());
			if (geolocation == null) {
				return;
			}
			if (requestCode == REQUEST_CREATE_GEOLOCATION) {
				GeoNotesManager.getInstance(this).addGeolocation(geolocation);
			} else if (requestCode == REQUEST_EDIT_GEOLOCATION) {
				GeoNotesManager.getInstance(this)
						.updateGeolocation(geolocation);
			}
			loadGeolocation();
			return;
		}
		super.onActivityResult(requestCode, resultCode, data);

	}

}
