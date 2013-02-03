package com.magkbdev.andtetris;

import org.andengine.entity.Entity;
import org.andengine.entity.scene.ITouchArea;
import org.andengine.entity.scene.Scene;
import org.andengine.input.touch.TouchEvent;
import org.andengine.util.Constants;
import org.andengine.util.adt.transformation.Transformation;

import android.util.Log;

public class GameScene extends Scene {
	
	private TetriminoEntity mActiveTetrimino; 
	
	public GameScene() { 
		super(); 
		
		/*
		registerTouchArea(new ITouchArea() {
			public boolean contains(float pX, float pY) {
				return true; 
			} 
			
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				
				return true;
			}
		}); 
		*/ 
	}
	@Override
	public boolean onSceneTouchEvent(TouchEvent pSceneTouchEvent) {
		// super.onSceneTouchEvent(pSceneTouchEvent);
		
		int eventAction = pSceneTouchEvent.getAction();
		switch (eventAction) {
			case TouchEvent.ACTION_DOWN:
				Log.d("GameScene::onSceneTouchEvent", "haha"); 
				mActiveTetrimino.rotate_cw(); 
				break; 
			case TouchEvent.ACTION_UP:
				break; 
			default:
				break;
		} 
		
		return false; 
	}

	public void addTetrimino(TetriminoEntity tetri) {
		mActiveTetrimino = tetri; 
		attachChild(tetri); 
	}
}
