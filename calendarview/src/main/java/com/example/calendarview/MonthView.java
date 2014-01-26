package com.example.calendarview;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.ValueAnimator;
import com.nineoldandroids.animation.ValueAnimator.AnimatorUpdateListener;

public class MonthView extends View {

	int hspace = 1;
	int vspace = 1;

	int cellWidth = 0;
	int cellHeight = 0;

	private View[][] childs = new View[6][7];

	public MonthView(Context context) {
		super(context);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		cellWidth = (getMeasuredWidth() - vspace * 8) / 7;
		cellHeight = cellWidth;
		int last = getMeasuredWidth() - (cellWidth * 7) - vspace * 8;
		for (int row = 0; row < 6; row++) {
			for (int col = 0; col < 7; col++) {
				int w = cellWidth;
				int h = cellHeight;
				if ((col + 1) > 7 - last) {
					w += 1;
				}
				View v = childs[row][col];
				if (v == null) {
					v = new DayView(getContext(), col + ":" + row);
					v.setLayoutParams(new ViewGroup.LayoutParams(w, h));
				}
				childs[row][col] = v;
				v.measure(MeasureSpec.makeMeasureSpec(w, MeasureSpec.EXACTLY),
						MeasureSpec.makeMeasureSpec(h, MeasureSpec.EXACTLY));
			}
		}
		setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), cellHeight
				* 6 + 7 * vspace);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		for (int row = 0; row < 6; row++) {
			for (int col = 0; col < 7; col++) {
				View v = childs[row][col];
				int saveCount = canvas.save();
				canvas.save();
				canvas.translate(v.getLeft(), v.getTop());
				v.draw(canvas);
				canvas.restore();
			}
		}
	}

	/**
	 * 
	 */
	public void shrink(int col) {
		Animator animator = getShinkAnimator(this, 0, -col * cellHeight - col
				* hspace);
		animator.setDuration(500);
		animator.start();
	}

	public static Animator getShinkAnimator(final View target, int start,
			int stop) {
		int b = target.getBottom();
		int e = b + stop;
		ValueAnimator animator = ValueAnimator.ofInt(b, e);
		animator.addUpdateListener(new AnimatorUpdateListener() {

			@Override
			public void onAnimationUpdate(ValueAnimator v) {
				int value = (Integer) v.getAnimatedValue();
				target.layout(target.getLeft(), target.getTop(),
						target.getRight(), value);
			}
		});
		return animator;
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		int hlastSize = getWidth() - cellWidth * 7 + 8 * vspace;
		int l, b = 0, t, r;
		l = left;
		t = hspace;
		for (int row = 0; row < 6; row++) {
			l = vspace;
			for (int col = 0; col < 7; col++) {
				View v = childs[row][col];
				r = l + v.getMeasuredWidth();
				b = t + v.getMeasuredHeight();
				System.out.println("r:" + r);
				if (v instanceof DayView) {
					DayView dv = (DayView) v;
					dv.setDay("" + (row * 7 + col));
				}
				v.layout(l, t, r, b);
				l = r + vspace;
			}
			t = b + hspace;
		}
	}

}
