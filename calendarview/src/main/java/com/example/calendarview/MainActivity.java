package com.example.calendarview;

import android.animation.Animator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.nineoldandroids.animation.TypeEvaluator;
import com.nineoldandroids.animation.ValueAnimator;
import com.nineoldandroids.animation.ValueAnimator.AnimatorUpdateListener;

public class MainActivity extends Activity {

	private View fram = null;
	private SwipeWrapper wrapper = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		wrapper = (SwipeWrapper) findViewById(R.id.monthView);
		wrapper.setContent(new MonthView(this));
		fram = findViewById(R.id.frame);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			startActivity(new Intent(this, SlideActivity.class));
		}
		return super.onTouchEvent(event);
	}

	public ValueAnimator createAnimator() {
		int dist = fram.getTop() + wrapper.getHeight();
		ValueAnimator animator = ValueAnimator.ofInt(fram.getTop(), dist);
		animator.addUpdateListener(new AnimatorUpdateListener() {

			@Override
			public void onAnimationUpdate(ValueAnimator vani) {
				int value = (Integer) vani.getAnimatedValue();
				fram.layout(fram.getLeft(), value, fram.getRight(),
						fram.getBottom());
			}
		});
		return animator;
	}
}
