package com.magkbdev.andtetris;

import org.andengine.entity.Entity;
import org.andengine.input.touch.TouchEvent;

public class TetriminoEntity extends Entity {
	
	private Tetrimino mTetrimino;
	private BlockRenderer[] mBlockRenderers;
	
	public TetriminoEntity(Tetrimino tetri, BlockRenderer[] blockRenderers) {
		mTetrimino = tetri; 
		mBlockRenderers = blockRenderers; 
		
		if (tetri != null && blockRenderers != null) {
			for (int i = 0; i < blockRenderers.length; ++i) { 
				attachChild(blockRenderers[i]); 
			}
		} 
		updatePosition(); 
	}
	
	public void updatePosition() {
		float blockTextureWidth = mBlockRenderers[0].getTextureRegion().getWidth(); 
		float blockTextureHeight = mBlockRenderers[0].getTextureRegion().getHeight(); 
		
		for (int i = 0; i < 4; ++i) { 
			int frameGridX = mTetrimino.getBlcokFrameGridX(i);
			int frameGridY = mTetrimino.getBlcokFrameGridY(i); 
			float screenX = this.mX + (frameGridX-1) * blockTextureWidth ;
			float screenY = this.mX + (frameGridY-1) * blockTextureHeight;
			mBlockRenderers[i].setPosition(screenX, screenY); 
		}
	}
	
	public void rotate_cw() {
		mTetrimino.rotate_cw(); 
		updatePosition(); 
	} 
	
	public void rotate_ccw() {
		mTetrimino.rotate_ccw(); 
		updatePosition(); 
	}
	
	public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		
		return true;
	}
}
