package com.emoroz.geonotes.adapters;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.emoroz.geonotes.R;
import com.emoroz.geonotes.entities.Geolocation;
import com.emoroz.geonotes.views.GeolocationItemView;

public class GeolocationAdapter extends AnimateAdapter<Geolocation> {

	public GeolocationAdapter(Context context, List<Geolocation> geolocations) {
		super(context, R.layout.item_geolocation, geolocations);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		GeolocationItemView view = (GeolocationItemView) convertView;
		if (view == null) {
			view = new GeolocationItemView(getContext());
		}
		view.setGeolocation(getItem(position));
		animate(position, view);
		return view;
	}

}
