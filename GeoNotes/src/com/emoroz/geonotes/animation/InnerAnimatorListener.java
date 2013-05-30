package com.emoroz.geonotes.animation;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.view.View;

public class InnerAnimatorListener implements AnimatorListener {

	private View view_;
	private int layerType_;

	public InnerAnimatorListener(View view) {
		view_ = view;
	}

	@Override
	public void onAnimationCancel(Animator animation) {
	}

	@Override
	public void onAnimationEnd(Animator animation) {
		view_.setLayerType(layerType_, null);
	}

	@Override
	public void onAnimationRepeat(Animator animation) {
	}

	@Override
	public void onAnimationStart(Animator animation) {
		layerType_ = view_.getLayerType();
		view_.setLayerType(View.LAYER_TYPE_HARDWARE, null);
	}

}
