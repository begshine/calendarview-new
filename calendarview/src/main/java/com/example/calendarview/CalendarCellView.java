package com.example.calendarview;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;

public class CalendarCellView extends Drawable {

	private int color = Color.RED;

	@Override
	public void draw(Canvas canvas) {
		canvas.drawColor(color);
	}

	public void setColor(int color) {
		this.color = color;
		invalidateSelf();
	}

	@Override
	public int getOpacity() {
		return 0;
	}

	@Override
	public void setAlpha(int alpha) {
	}

	@Override
	public void setColorFilter(ColorFilter cf) {
	}

}
