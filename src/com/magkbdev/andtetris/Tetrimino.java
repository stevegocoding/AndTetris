package com.magkbdev.andtetris;

class Point 
{
	public int x; 
	public int y;
	
	public Point(int x, int y) {
		this.x = x; 
		this.y = y; 
	}
}

public class Tetrimino {
	private int[][][] mLocalGrid;
	private Point[][] mLocalBlocksPositions;
	
	private final TetriminosShape mShape;
	private int mCurrentPosition;

	private int mFrameGridX = 0;
	private int mFrameGridY = 0;

	public Tetrimino(final TetriminosShape shape, final int frameGridX, final int frameGridY) {
		mShape = shape;
		mCurrentPosition = 0;
		mFrameGridX = frameGridX;
		mFrameGridY = frameGridY;
		
		initLocalGrid();
		preCalculatPositionsGrid();
		preCalculateBlockPositions(); 
	}

	public void rotate() {
		mCurrentPosition = (mCurrentPosition + 1) % mShape.mNumPositions;
	}
	
	public TetriminosShape getShape() {
		return mShape; 
	}

	public int[][][] getLocalGrid() {
		return mLocalGrid;
	}

	public int getCurrentPosition() {
		return mCurrentPosition;
	}

	public int getBlcokFrameGridX(int blockIdx) {
		if (blockIdx >= 0 && blockIdx < 4) {
			return mFrameGridX + mLocalBlocksPositions[mCurrentPosition][blockIdx].x;
		}
		return -1; 
	}

	public int getBlcokFrameGridY(int blockIdx) {
		if (blockIdx >= 0 && blockIdx < 4) {
			return mFrameGridY + mLocalBlocksPositions[mCurrentPosition][blockIdx].y;
		}
		return -1; 
	}
	
	private void initLocalGrid() {
		if (mLocalGrid == null) {
			mLocalGrid = new int[mShape.mNumPositions][][];
			for (int pos = 0; pos < mShape.mNumPositions; ++pos) {
				int dim = mShape.mGridDimension;
				mLocalGrid[pos] = new int[dim][dim];
				for (int i = 0; i < dim * dim; ++i) {
					mLocalGrid[pos][i / dim][i % dim] = 0;
				}
			}
		}
	}

	private int[][] rotate_90_ccw(int[][] mat, int[][] outputMat) {
		int n = mat.length;
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				outputMat[n - 1 - j][i] = mat[i][j];
			}
		}
		return outputMat;
	}

	private void preCalculatPositionsGrid() {
		// Copy the grid for the default position
		for (int i = 0; i < mShape.mGridDimension; ++i)
			mLocalGrid[0][i] = mShape.mGrid[i].clone();

		int[][] tempGrid = new int[mShape.mGridDimension][mShape.mGridDimension];
		for (int pos = 1; pos < mShape.mNumPositions; ++pos) {
			int[][] rotatedGrid = rotate_90_ccw(mLocalGrid[pos - 1], tempGrid);

			// Copy the rotated grid
			for (int i = 0; i < mShape.mGridDimension; ++i)
				mLocalGrid[pos][i] = rotatedGrid[i].clone();
		}
	}
	
	private void preCalculateBlockPositions() {
		
		if (mLocalBlocksPositions == null) {
			mLocalBlocksPositions = new Point[mShape.mNumPositions][4];
			for (int pos = 0; pos < mShape.mNumPositions; ++pos) {
				mLocalBlocksPositions[pos] = new Point[4];
			}
		}
		
		for (int pos = 0; pos < mShape.mNumPositions; ++pos) {
			mLocalBlocksPositions[pos] = new Point[4];
			int blockIdx = 0; 
			for (int i = 0; i < mShape.mGridDimension; ++i) {
				for (int j = 0; j < mShape.mGridDimension; ++j) {
					if (mLocalGrid[pos][i][j] != 0) {
						mLocalBlocksPositions[pos][blockIdx++] = new Point(i, j); 
					}
				}
			}
		}
	}
}
