package com.example.calendarview;

import android.graphics.Path.Direction;
import android.view.MotionEvent;

/**
 * 拖动事件处理
 * 
 * @author javen
 * 
 */
public class SwipeEventProcesser {

	public static final int DIRECT_H = 1;// 横向拖动
	public static final int DIRECT_V = 2;// 纵向

	/**
	 * 
	 * @param direct
	 *            方向：@see {@link DIRECT_H,DIRECT_V}
	 * @param sloup
	 *            有效触发
	 */
	public SwipeEventProcesser(int direct, int sloup) {

	}

	/**
	 * 
	 * @param event
	 */
	public boolean onTouchEvent(MotionEvent event) {

		return true;
	}

}
