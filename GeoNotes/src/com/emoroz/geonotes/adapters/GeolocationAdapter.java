package com.emoroz.geonotes.adapters;

import java.util.List;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationSet;
import android.widget.ArrayAdapter;

import com.emoroz.geonotes.R;
import com.emoroz.geonotes.animation.InnerAnimatorListener;
import com.emoroz.geonotes.entities.Geolocation;
import com.emoroz.geonotes.views.GeolocationItemView;

public class GeolocationAdapter extends ArrayAdapter<Geolocation> {

	private static final float ANIM_X = 50;
	private static final float ANIM_Y = 50;

	private int previousPosition_ = -1;
	private boolean animate_ = false;

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

	protected void animate(int position, View view) {
		float skewX = 0.15f;
		float translationX;
		float translationY;
		if (animate_) {
			if (previousPosition_ < position) {
				translationX = ANIM_X;
				translationY = ANIM_Y;
			} else {
				translationX = -ANIM_X;
				translationY = -ANIM_Y;
			}
			
			ObjectAnimator skewAnimator = ObjectAnimator.ofFloat(view, "skewX", skewX, 0f);
			ObjectAnimator translationXAnimator = ObjectAnimator.ofFloat(view, View.TRANSLATION_X, translationX, 0f);
			ObjectAnimator translationYAnimator = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, translationY, 0f);
			
			AnimatorSet set = new AnimatorSet();
			set.playTogether(skewAnimator, translationXAnimator, translationYAnimator);
			set.setDuration(400);
			set.start();
		}
		previousPosition_ = position;
	}

	public void setAnimate(boolean animate) {
		animate_ = animate;
	}
}
