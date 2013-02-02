package com.magkbdev.andtetris;

import org.andengine.entity.Entity;

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
			Block block = mBlockRenderers[i].getBlock();
			float screenX = this.mX + block.mFrameGridX * blockTextureWidth;
			float screenY = this.mX + block.mFrameGridY * blockTextureHeight;
			mBlockRenderers[i].setPosition(screenX, screenY); 
		}
	}

}
