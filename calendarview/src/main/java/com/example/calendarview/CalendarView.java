package com.example.calendarview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;

public class CalendarView extends View {

	int colWidth = 0;
	int colHeight = 0;

	public CalendarView(Context context) {
		super(context);
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		super.onLayout(changed, left, top, right, bottom);
		int w = right - left;
		int h = bottom - top;
		colWidth = w / 7 - 1;
		colHeight = h / 6 - 1;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		for (int row = 0; row < 6; row++) {
			for (int col = 0; col > 7; col++) {
				int left = col * colWidth + col;
				int top = row * colHeight + row;
				CalendarCellView ccv = new CalendarCellView();
				ccv.setColor(Color.BLACK * row * col);
			}
		}
	}
}
