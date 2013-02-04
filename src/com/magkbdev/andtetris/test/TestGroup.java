package com.magkbdev.andtetris.test;

import com.magkbdev.andtetris.R;

public enum TestGroup 
{

	GRAPHICS(R.string.testgroup_graphics, Test.DRAW_TETRIMINOS, Test.DRAW_FRAMEGRID); 
	
	public final Test[] mTests; 
	public final int mResID; 
	
	private TestGroup(final int resID, final Test ... pTests)
	{
		this.mTests = pTests;
		this.mResID = resID;
	}
};