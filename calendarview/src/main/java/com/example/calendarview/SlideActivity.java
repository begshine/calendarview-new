package com.example.calendarview;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

/**
 * SlideActivity
 * 
 * @author javen
 * 
 */
public class SlideActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().getDecorView().setBackgroundColor(Color.BLUE);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_MOVE) {
			int left = (int) event.getX();
			getWindow().getDecorView().layout(left, 0,
					left + getWindow().getDecorView().getWidth(),
					getWindow().getDecorView().getHeight());
		} else if (event.getAction() == MotionEvent.ACTION_UP) {
			if (getWindow().getDecorView().getLeft() != 0) {
				finish();
			}
		}
		return super.onTouchEvent(event);
	}
}
