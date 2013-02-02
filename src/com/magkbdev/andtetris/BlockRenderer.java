package com.magkbdev.andtetris;

import org.andengine.engine.Engine;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;

public class BlockRenderer extends Sprite {

	private Block mBlock; 
	
	public BlockRenderer(Block block, ITextureRegion texRegion, Engine engine) {
		super(0, 0, texRegion, engine.getVertexBufferObjectManager()); 
		
		mBlock = block; 
	}
	
	public void setBlcok(Block block) {
		mBlock = block; 
	}
	
	public Block getBlock() {
		return mBlock; 
	} 
	
	@Override
	public void reset() {
		super.reset(); 
		
		mBlock = null;
	}
}
