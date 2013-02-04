package com.magkbdev.andtetris.test;

import org.andengine.ui.activity.SimpleBaseGameActivity;

import com.magkbdev.andtetris.R;

enum Test 
{
	DRAW_TETRIMINOS (DrawTetriminosTest.class, R.string.draw_tetriminos_test),
	DRAW_FRAMEGRID (DrawFrameGridTest.class, R.string.draw_framegrid_test); 
	
	public final Class<? extends SimpleBaseGameActivity> CLASS; 
	public final int RESID;
	
	private Test(final Class<? extends SimpleBaseGameActivity> testClass, final int resID)
	{
		this.CLASS = testClass; 
		this.RESID = resID; 
	} 
};
