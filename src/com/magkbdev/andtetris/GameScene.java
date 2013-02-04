package com.magkbdev.andtetris;

import org.andengine.engine.Engine;
import org.andengine.entity.primitive.Line;
import org.andengine.entity.scene.Scene;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

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
				if (mActiveTetrimino != null)
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
	
	public void addGrid(Line grid) {
		attachChild(grid);
	}
	
	public Line buildGrid(int pWidth, int pHeight, Engine engine) {
		final VertexBufferObjectManager vboManager = engine.getVertexBufferObjectManager(); 
		
        Line grid = new Line(0, 0, 0, pHeight, 1.0f, vboManager);
        grid.setColor(0.5f, 0.5f, 0.5f);
        int cont = 0;
       
        while(cont < pWidth){
                cont += 32;
                grid.attachChild(new Line(cont, 0, cont, pHeight, 1.0f, vboManager));
                grid.getLastChild().setColor(1.0f, 1.0f, 1.0f);                     
        }
       
        cont = 0;
        while (cont < pHeight){
                cont += 32;
                grid.attachChild(new Line(0, cont, pWidth, cont, 1.0f, vboManager));
                grid.getLastChild().setColor(1.0f, 1.0f, 1.0f);
        }
       
        return grid;
	}
}
