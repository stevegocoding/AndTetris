package com.magkbdev.andtetris.test;

import com.magkbdev.andtetris.R; 
import com.magkbdev.andtetris.test.DrawTetriminosTest;
import org.andengine.ui.activity.BaseGameActivity;

enum Test 
{
	DRAW_TETRIMINOS (DrawTetriminosTest.class, R.string.draw_tetriminos_test); 
	
	public final Class<? extends BaseGameActivity> CLASS; 
	public final int RESID;
	
	private Test(final Class<? extends BaseGameActivity> testClass, final int resID)
	{
		this.CLASS = testClass; 
		this.RESID = resID; 
	} 
};
