package com.example.calendarview;

import android.content.ContentValues;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.nineoldandroids.animation.ValueAnimator;
import com.nineoldandroids.animation.ValueAnimator.AnimatorUpdateListener;

public class SwipeWrapper extends FrameLayout {

	private ImageView coverImage = null;

	private View contentView = null;

	private float lastMotionX = 0;

	private boolean canSwipe = true;

	public SwipeWrapper(Context context, AttributeSet attr) {
		super(context, attr);
		coverImage = new ImageView(getContext());
		coverImage.setScaleType(ScaleType.FIT_START);
		addView(coverImage, new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
	}

	public void setContent(View ctView) {
		this.contentView = ctView;
		addView(this.contentView);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		final int action = event.getAction();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			onTouchDown(event);
			break;
		case MotionEvent.ACTION_MOVE:
			onTouchMove(event);
			break;
		case MotionEvent.ACTION_CANCEL:
		case MotionEvent.ACTION_UP:
			onTouchUp(event);
			break;
		}
		return canSwipe;
	}

	private void onTouchDown(MotionEvent event) {
		lastMotionX = event.getX();
		canSwipe = true;
	}

	private void onTouchMove(MotionEvent event) {
		final float x = event.getX();
		final int deltaX = (int) (lastMotionX - x);
		lastMotionX = x;
		if (deltaX < 0) {
			canSwipe = false;
			swipeToLeft();
		} else if (deltaX > 0) {
			canSwipe = false;
			swipeToRight();
		}

	}

	boolean flag = true;

	private void swipeToLeft() {
		contentView.destroyDrawingCache();
		contentView.setDrawingCacheBackgroundColor(Color.RED);
		contentView.setDrawingCacheEnabled(true);
		contentView.buildDrawingCache();
		coverImage.setImageBitmap(contentView.getDrawingCache());
		bringChildToFront(coverImage);
		AnimationSet anim = new AnimationSet(true);
		Animation slideOut = AnimationUtils.loadAnimation(getContext(),
				R.anim.slide_out_left);
		Animation slideIn = AnimationUtils.loadAnimation(getContext(),
				R.anim.slide_in_right);
		slideOut.setDuration(800);
		slideIn.setDuration(800);
		MonthView mv = (MonthView) contentView;
		com.nineoldandroids.animation.ValueAnimator animator = ValueAnimator
				.ofInt(getBottom(), getBottom() + (flag ? 1 : -1) * 100);
		
		animator.addUpdateListener(new AnimatorUpdateListener() {

			@Override
			public void onAnimationUpdate(ValueAnimator arg0) {
				int value = (Integer) arg0.getAnimatedValue();
				layout(getLeft(), getTop(), getRight(), value);
			}
		});
		animator.setDuration(400);
		animator.start();
		coverImage.startAnimation(slideOut);
		contentView.startAnimation(slideIn);
		slideOut.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				coverImage.setVisibility(View.INVISIBLE);
			}
		});
		flag = !flag;
	}

	private void swipeToRight() {

	}

	private void onTouchUp(MotionEvent event) {

	}

}
