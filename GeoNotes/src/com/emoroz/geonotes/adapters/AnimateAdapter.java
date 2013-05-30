package com.emoroz.geonotes.adapters;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;

import com.emoroz.geonotes.animation.InnerAnimatorListener;

public class AnimateAdapter<T> extends ArrayAdapter<T> {

	private static final float animX = 50;
	private static final float animY = 50;

	private int previousPosition_ = -1;
	private boolean animate_ = false;

	public AnimateAdapter(Context context, int textViewResourceId,
			List<T> objects) {
		super(context, textViewResourceId, objects);
	}

	protected void animate(int position, View view) {
		if (animate_) {
			if (previousPosition_ < position) {
				view.setTranslationX(animX);
				view.setTranslationY(animY);
			} else {
				view.setTranslationX(-animX);
				view.setTranslationY(-animY);
			}
			view.animate().translationX(0).translationY(0).setDuration(400)
					.setListener(new InnerAnimatorListener(view)).start();
		}
		previousPosition_ = position;
	}

	public void setAnimate(boolean animate) {
		animate_ = animate;
	}

}
