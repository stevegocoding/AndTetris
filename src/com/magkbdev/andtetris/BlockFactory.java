package com.magkbdev.andtetris;

import org.andengine.engine.Engine;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.bitmap.BitmapTexture;
import org.andengine.opengl.texture.region.ITextureRegion;

import android.util.SparseArray;

public class BlockFactory {
	
	private Block[] mBlocksPool;
	private int mMaxBlocks; 
	private int mFreeBlocks;
	
	private BitmapTexture mTexture;
	private SparseArray<ITextureRegion> mTextureRegions;
	
	private Engine mEngine; 
	
	public BlockFactory(final int maxBlocks, final BitmapTexture texture, final SparseArray<ITextureRegion> textureRegions, Engine engine) {
		this.mMaxBlocks = maxBlocks; 
		this.mTexture = texture; 
		this.mTextureRegions = textureRegions;
		this.mEngine = engine; 
		
		resetBlocksPool(); 
	}
	
	public Tetriminos createTetriminos(final TetriminosShape shape, final int frameGridX, final int frameGridY) {
		Tetriminos tetri = new Tetriminos(shape, frameGridX, frameGridY); 
		
		return tetri;
	}
	
	public Block[] createTeriminosBlocks(Tetriminos tetri) {
		Block[] blocks = new Block[4];
		
		for (int i = 0; i < 4; ++i) {
			blocks[i] = mBlocksPool[mFreeBlocks++]; 
			int frameGridX = tetri.getBlcokFrameGridX(i);
			int frameGridY = tetri.getBlcokFrameGridY(i); 
			Sprite sprite = createSprite(tetri.getShape().mBlockSpriteID); 
			blocks[i].setFrameGridXY(frameGridX, frameGridY); 
			blocks[i].setSprite(sprite); 
		}
		
		return blocks;
	}
	
	private Sprite createSprite(int blockID) {
		ITextureRegion texRegion = mTextureRegions.get(blockID); 
		Sprite sprite = new Sprite(0.0f, 0.0f, texRegion, mEngine.getVertexBufferObjectManager()); 
		
		return sprite;
	}
	
	private void resetBlocksPool() {
		
		/// The blocks pool is null, so create it
		if (mBlocksPool == null)  {
			mBlocksPool = new Block[mMaxBlocks]; 
			for (int i = 0; i < mBlocksPool.length; ++i) {
				mBlocksPool[i] = new Block(-1, -1); 
			}
		} else {
			for (int i = 0; i < mBlocksPool.length; ++i) {
				mBlocksPool[i].reset(); 
			}
		}
	} 
}
