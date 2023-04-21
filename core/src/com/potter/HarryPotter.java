package com.potter;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class HarryPotter extends Game {

	private SpriteBatch spriteBatch;
	private BitmapFont bitmapFont;

	@Override
	public void create () {

		spriteBatch = new SpriteBatch();
		bitmapFont = new BitmapFont();
		setScreen(new GameplayScreen(this ));
	}

	@Override
	public void dispose () {
		super.dispose();
		spriteBatch.dispose();
		bitmapFont.dispose();
	}

	public SpriteBatch getSpriteBatch() {
		return spriteBatch;
	}

	public BitmapFont getBitmapFont() {
		return bitmapFont;
	}
}
