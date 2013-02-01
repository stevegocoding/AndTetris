package com.magkbdev.andtetris.test;

import com.magkbdev.andtetris.R; 
import org.andengine.ui.activity.SimpleBaseGameActivity;

enum Test 
{
	DRAW_TETRIMINOS (DrawTetriminosTest.class, R.string.draw_tetriminos_test); 
	
	public final Class<? extends SimpleBaseGameActivity> CLASS; 
	public final int RESID;
	
	private Test(final Class<? extends SimpleBaseGameActivity> testClass, final int resID)
	{
		this.CLASS = testClass; 
		this.RESID = resID; 
	} 
};
