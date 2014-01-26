package com.example.calendarview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.widget.AdapterView;
import android.widget.HorizontalScrollView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DayView extends RelativeLayout {

	private String col = "";

	public DayView(Context context, String col) {
		super(context);
		inflate(context, R.layout.day_view, this);
		this.col = col;
		setBackgroundColor(Color.WHITE);
	}

	public void setDay(String d) {
		TextView dt = (TextView) findViewById(R.id.dayview_text_day);
		dt.setText(d);
	}
}
