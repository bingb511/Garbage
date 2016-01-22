package net.blogjava.mobile.calendar;


import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.widget.TextView;
/*
 * @about
 */

public class TestView extends SimpleOnGestureListener
{
	public Calendar ce;

	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub
		ce.grid.setCellX(e.getX());
		ce.grid.setCellY(e.getY());
		if (ce.grid.inBoundary()) {
			
		}
		
		super.onLongPress(e);
	}

      
}
