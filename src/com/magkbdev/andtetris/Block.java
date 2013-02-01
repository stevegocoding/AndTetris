package com.magkbdev.andtetris;

import org.andengine.entity.sprite.Sprite;


public class Block {
	
	public int mFrameGridX; 
	public int mFrameGridY; 
	private Sprite mSprite; 
	
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
	
	public void setSprite(final Sprite sprite) {
		this.mSprite = sprite; 
	}
	
	public Sprite getSprite() {
		return mSprite; 
	}
}
