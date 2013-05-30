package com.emoroz.geonotes.views;

import android.app.Activity;
import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.emoroz.geonotes.R;
import com.emoroz.geonotes.entities.Geolocation;

public class GeolocationItemView extends LinearLayout {

	private TextView name_;

	public GeolocationItemView(Context context) {
		super(context);

		((Activity) context).getLayoutInflater().inflate(
				R.layout.item_geolocation, this, true);

		name_ = (TextView) findViewById(R.id.name);
	}
	
	public void setGeolocation(Geolocation geolocation) {
		name_.setText(geolocation.getName());
		name_.setCompoundDrawablesWithIntrinsicBounds(geolocation.getDrawableId(), 0, 0, 0);
	}

}
