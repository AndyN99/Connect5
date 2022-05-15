package com.mygame.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Connect5Game extends Game {

	GameScreen screen;
	@Override
	public void create() {
		screen = new GameScreen();
		setScreen(screen);
	}

	@Override
	public void resize(int width, int height) {
		screen.resize(width, height);
	}

	@Override
	public void dispose() {
		super.dispose();
		screen.dispose();
	}

	@Override
	public void render() {
		super.render();
	}
}