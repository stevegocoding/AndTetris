package com.magkbdev.andtetris;

import org.andengine.engine.Engine;
import org.andengine.opengl.texture.bitmap.BitmapTexture;
import org.andengine.opengl.texture.region.ITextureRegion;

import android.util.SparseArray;

class BlocksPool {
	
	private Block[] mBlocks;
	private int mNextFree; 
	
	public BlocksPool(int maxObjects) {
		resetPool(maxObjects); 
	}
	
	public void resetPool(int maxObjects) {
		
		/// The blocks pool is null, so create it
		if (mBlocks == null)  {
			mBlocks = new Block[maxObjects]; 
			for (int i = 0; i < mBlocks.length; ++i) {
				mBlocks[i] = new Block(-1, -1); 
			}
		} else {
			for (int i = 0; i < mBlocks.length; ++i) {
				mBlocks[i].reset(); 
			}
		}
		
		mNextFree= 0; 
	} 
	
	Block allocateBlock() {
		// If there is no more free object in the pool
		if (mNextFree >= (mBlocks.length - 1)) {
			return null;
		}
		else { 
			Block ret = mBlocks[mNextFree];
			mNextFree++; 
			return ret; 
		}
	}
	
	void recycleBlock(Block block) {
		if (mNextFree > 0 && block != null) {
			mNextFree--; 
			block.reset(); 
			mBlocks[mNextFree] = block; 
		}
	}
}

class BlockRenderersPool {
	
	private BlockRenderer[] mBlockRenderers; 
	private int mNextFree; 
	private ITextureRegion mTextureRegion; 
	private Engine mEngine; 
	
	public BlockRenderersPool(int maxObjects, ITextureRegion texRegion, Engine engine) { 
		mTextureRegion = texRegion; 
		mEngine = engine; 
		
		resetPool(maxObjects);
	}
	
	void resetPool(int maxObjects) { 
		/// The blocks pool is null, so create it
		if (mBlockRenderers == null)  {
			mBlockRenderers = new BlockRenderer[maxObjects]; 
			for (int i = 0; i < mBlockRenderers.length; ++i) {
				mBlockRenderers[i] = new BlockRenderer(null, mTextureRegion, mEngine); 
			}
		} else {
			for (int i = 0; i < mBlockRenderers.length; ++i) {
				mBlockRenderers[i].reset(); 
			}
		}		
		mNextFree= 0; 
	} 
	
	BlockRenderer allocateBlockRenderer(Block block) {
		// If there is no more free object in the pool
		if (mNextFree >= (mBlockRenderers.length - 1)) {
			return null;
		}
		else { 
			BlockRenderer ret = mBlockRenderers[mNextFree];
			ret.setBlcok(block); 
			mNextFree++; 
			return ret; 
		}
	}
}

public class BlockFactory {
	
	private BlocksPool mBlocksPool;
	private BlockRenderersPool[] mBlockRenderersPools; 
	
	/*
	private BitmapTexture mTexture;
	private SparseArray<ITextureRegion> mTextureRegions; 
	private Engine mEngine; 
	*/
	
	public BlockFactory(final int maxBlocks, final BitmapTexture texture, final SparseArray<ITextureRegion> textureRegions, Engine engine) {
		/*
		this.mTexture = texture; 
		this.mTextureRegions = textureRegions;
		this.mEngine = engine; 
		*/
		
		int numBlockColors = textureRegions.size(); 
		mBlocksPool = new BlocksPool(maxBlocks); 
		mBlockRenderersPools = new BlockRenderersPool[numBlockColors]; 
		for (int i = 0; i < numBlockColors; ++i) {
			mBlockRenderersPools[i] = new BlockRenderersPool(maxBlocks, textureRegions.get(i), engine);
		} 
	}
	
	public Tetrimino createTetriminos(final TetriminosShape shape, final int frameGridX, final int frameGridY) {
		Tetrimino tetri = new Tetrimino(shape, frameGridX, frameGridY);  
		return tetri;
	}
	
	public BlockRenderer[] createTetriBlocksRenderer(Tetrimino tetri) {
		BlockRenderer[] blocks = new BlockRenderer[4];
		
		for (int i = 0; i < 4; ++i) {
			int colorID = tetri.getShape().mBlockSpriteID;
			Block block = mBlocksPool.allocateBlock(); 
			block.mFrameGridX = tetri.getBlcokFrameGridX(i); 
			block.mFrameGridY = tetri.getBlcokFrameGridY(i); 
			if (block != null) {
				blocks[i] = mBlockRenderersPools[colorID].allocateBlockRenderer(block);
				if (blocks[i] == null)
					return null; 
			}
			else 
				return null; 
		}
		
		return blocks;
	}  
}
