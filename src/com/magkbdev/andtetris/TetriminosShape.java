package com.magkbdev.andtetris;

class BlockColor {
	
	public static final int BLOCK_BLUE_ID = 0;
	public static final int BLOCK_GREEN_ID = 1;
	public static final int BLOCK_PINK_ID = 2;
	public static final int BLOCK_RED_ID = 3;
	public static final int BLOCK_WHITE_ID = 4;
	public static final int BLOCK_YELLOW_ID = 5;
}

public enum TetriminosShape {

	I_SHAPE(0, 4, 2, BlockColor.BLOCK_BLUE_ID), 
	J_SHAPE(1, 3, 4, BlockColor.BLOCK_GREEN_ID), 
	L_SHAPE(2, 3, 4, BlockColor.BLOCK_PINK_ID),
	S_SHAPE(3, 3, 2, BlockColor.BLOCK_RED_ID),
	Z_SHAPE(4, 3, 2, BlockColor.BLOCK_WHITE_ID),
	O_SHAPE(5, 2, 1, BlockColor.BLOCK_YELLOW_ID), 
	T_SAHPE(6, 3, 4, BlockColor.BLOCK_WHITE_ID); 
		
	public final int mShapeID; 
	/**
	 * I - 0
	 * J - 1
	 * L - 2
	 * S - 3
	 * Z - 4
	 * O - 5
	 * T - 6
	 */
	public final int mGridDimension; 
	public final int mNumPositions;
	public final int[][] mGrid; 
	public final int mBlockSpriteID; 
	
	private TetriminosShape(final int shapeID, final int gridDimension, final int numPositions, final int blockSpriteID)
	{
		mShapeID = shapeID; 
		mGridDimension = gridDimension;
		mNumPositions = numPositions;
		mBlockSpriteID = blockSpriteID;
		
		mGrid = new int[mGridDimension][mGridDimension];
		
		switch(mShapeID)
		{
		case 0:
			int[][] g0 = {
					{0, 0, 0, 0},
					{1, 1, 1, 1},
					{0, 0, 0, 0},
					{0, 0, 0, 0}}; 
			for (int i = 0; i < mGridDimension; ++i)
				mGrid[i] = g0[i].clone(); 
			break;
			/*
		case 1:
			int[][] g1= {
					{1, 1, 1},
					{0, 0, 1},
					{0, 0, 0}};
			for (int i = 0; i < mGridDimension; ++i)
				mGrid[i] = g1[i].clone(); 
			break; 
		case 2:
			int[][] g2 = {
					{1, 1, 1},
					{1, 0, 0},
					{0, 0, 0}};
			for (int i = 0; i < mGridDimension; ++i)
				mGrid[i] = g2[i].clone(); 
			break;
			*/
		case 1:
			int[][] g1= {
					{0, 0, 0},
					{1, 1, 1},
					{0, 0, 1}};
			for (int i = 0; i < mGridDimension; ++i)
				mGrid[i] = g1[i].clone(); 
			break; 
		case 2:
			int[][] g2 = {
					{0, 0, 0},
					{1, 1, 1},
					{1, 0, 0}};
			for (int i = 0; i < mGridDimension; ++i)
				mGrid[i] = g2[i].clone(); 
			break;
			
		case 3:
			int[][] g3 = {
					{0, 1, 1},
					{1, 1, 0},
					{0, 0, 0}};
			for (int i = 0; i < mGridDimension; ++i)
				mGrid[i] = g3[i].clone(); 
			break;
		case 4:
			int[][] g4 = {
					{1, 1, 0},
					{0, 1, 1},
					{0, 0, 0}};
			for (int i = 0; i < mGridDimension; ++i)
				mGrid[i] = g4[i].clone(); 
			break;
		case 5:
			int[][] g5 = {
					{1, 1},
					{1, 1}};
			for (int i = 0; i < mGridDimension; ++i)
				mGrid[i] = g5[i].clone(); 
			break;
		case 6: 
			int[][] g6 = {
					{1, 1, 1},
					{0, 1, 0},
					{0, 0, 0}};
			for (int i = 0; i < mGridDimension; ++i)
				mGrid[i] = g6[i].clone(); 
			break; 
		}
	}
}
