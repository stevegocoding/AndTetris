package com.magkbdev.andtetris.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.Arrays;
import java.util.HashMap;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.primitive.Line;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.util.FPSLogger;
import org.andengine.opengl.texture.bitmap.BitmapTexture;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TextureRegionFactory;
import org.andengine.ui.activity.SimpleBaseGameActivity;
import org.andengine.util.adt.io.in.IInputStreamOpener;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.SparseArray;

import com.magkbdev.andtetris.GameScene;

public class DrawFrameGridTest extends SimpleBaseGameActivity {

	private static final int CAMERA_WIDTH = 480;
	private static final int CAMERA_HEIGHT = 800;

	private HashMap<String, BitmapTexture> mTexturesMap = new HashMap<String, BitmapTexture>();
	private SparseArray<ITextureRegion> mTextureRegions = new SparseArray<ITextureRegion>();

	private GameScene mGameScene; 
	
	public EngineOptions onCreateEngineOptions() {
		final Camera camera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);

		return new EngineOptions(true, ScreenOrientation.PORTRAIT_FIXED,
				new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), camera);
	}

	public void onCreateResources() {
		//loadTextures(this, getEngine()); 
		// String xmlFilePath = "config/" + BLOCKS_SHEET;
		// parseTextureRegionsXML(this, xmlFilePath); 
	}
	
	public Scene onCreateScene() {
		this.mEngine.registerUpdateHandler(new FPSLogger());
		
		mGameScene = new GameScene(); 
		mGameScene.setBackground(new Background(0.09804f, 0.6274f, 0.8784f)); 
		
		Line grid = mGameScene.buildGrid(320, 640, this.getEngine()); 
		// grid.setPosition(mGameScene);
		mGameScene.addGrid(grid);
		
		return mGameScene;
	}
	
	
	private String[] mFileNames; 
	private String[] mFolderNames = new String[] { new String("gfx") };

	private int loadTextures(Context context, Engine engine) {
		BitmapFactory.Options opt = new BitmapFactory.Options();
		opt.inJustDecodeBounds = true;

		for (int i = 0; i < mFolderNames.length; ++i) {
			try {
				mFileNames = context.getResources().getAssets().list(mFolderNames[i]);
				Arrays.sort(mFileNames); 
				for (int j = 0; j < mFileNames.length; ++j) {
					final String resPath = mFolderNames[i] + "/" + mFileNames[j];
					/*
					InputStream in = context.getResources().getAssets().open(resPath);
					BitmapFactory.decodeStream(in, null, opt);

					int width = opt.outWidth;
					int height = opt.outHeight;
					boolean isPowerOfTwo = MathUtils.isPowerOfTwo(width);
					if (!isPowerOfTwo)
						width = MathUtils.nextPowerOfTwo(opt.outWidth);
					isPowerOfTwo = MathUtils.isPowerOfTwo(height);
					if (!isPowerOfTwo)
						height = MathUtils.nextPowerOfTwo(opt.outHeight);
					 */
					
					BitmapTexture tex = new BitmapTexture(engine.getTextureManager(), new IInputStreamOpener() {
						public InputStream open() throws IOException {
							return getAssets().open(resPath);
						}
					});
					
					mTexturesMap.put(mFileNames[j], tex);
					mTexturesMap.get(mFileNames[j]).load();
					// engine.getTextureManager().loadTexture(mTexturesMap.get(mFileNames[j]));
				}
			} catch (IOException e) {
				e.printStackTrace();
				return 1;
			}
		}
		return 0;
	}

	private int parseTextureRegionsXML(Context context, final String xmlFile) {
		// Read the file to a string
		InputStream is;
		StringBuffer fileContent = new StringBuffer("");
		try {
			is = context.getResources().getAssets().open(xmlFile); 
			byte[] buffer = new byte[1024];
			int length = 0; 
			while ((length = is.read(buffer)) != -1) {
				fileContent.append(new String(buffer, 0, length));
			}

			/// Parse the xml 
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			factory.setNamespaceAware(true);
			XmlPullParser xpp = factory.newPullParser();
			xpp.setInput(new StringReader(fileContent.toString()));

			String currentTexture = new String(); 
			int eventType = xpp.getEventType();
			while (eventType != XmlPullParser.END_DOCUMENT) {
				if (eventType == XmlPullParser.START_TAG) {
					if (xpp.getName().compareTo("texture") == 0) {
						currentTexture = xpp.getAttributeValue(1);
					} else if (xpp.getName().compareTo("textureregion") == 0) {
						String idStr = xpp.getAttributeValue(null, "id"); 
						String xStr = xpp.getAttributeValue(null, "x");
						String yStr = xpp.getAttributeValue(null, "y"); 
						String widthStr = xpp.getAttributeValue(null, "width"); 
						String heightStr = xpp.getAttributeValue(null, "height");
						String rotatedStr = xpp.getAttributeValue(null, "rotated"); 
						BitmapTexture texure = mTexturesMap.get(currentTexture);
						ITextureRegion texRegion = TextureRegionFactory.extractFromTexture(texure, 
																Integer.parseInt(xStr), 
																Integer.parseInt(yStr), 
																Integer.parseInt(widthStr), 
																Integer.parseInt(heightStr), 
																Boolean.parseBoolean(rotatedStr));
						mTextureRegions.append(Integer.parseInt(idStr), texRegion); 
					}
				}
				eventType = xpp.next();
			}
		} catch (IOException e) {
			e.printStackTrace();
			return 1;
		} catch (XmlPullParserException e) {
			e.printStackTrace();
			return 1;
		}

		return 0;
	} 
	
}
