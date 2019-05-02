package com.atm.game;

import com.atm.game.screens.GameScreen;
import com.atm.game.screens.MenuScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Game extends com.badlogic.gdx.Game {
	public final static int WIDTH = 1280;
	public final static int HEIGHT = 720;
	public final static int TILEWIDTH = 128;
	public final static int TILEHEIGHT = 64;

	@Override
	public void create () {
		this.setScreen(new GameScreen(this));
	}


	@Override
	public void dispose () {
	}
}
