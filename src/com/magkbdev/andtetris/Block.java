package com.magkbdev.andtetris;


public class Block {
	
	public int mFrameGridX; 
	public int mFrameGridY; 

	public Block(final int frameGridX, final int frameGridY) {
		this.mFrameGridX = frameGridX; 
		this.mFrameGridY = frameGridY;  
	}
	
	public void reset() {
		this.mFrameGridX = -1; 
		this.mFrameGridY = -1; 
	}

	public void setFrameGridXY(final int frameGridX, final int frameGridY) {
		this.mFrameGridX = frameGridX; 
		this.mFrameGridY = frameGridY; 
	} 
}
