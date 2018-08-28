package com.mre.game.snake;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mre.game.snake.screen.GameOverScreen;
import com.mre.game.snake.screen.MenuScreen;
import com.mre.game.snake.screen.PlayScreen;

public class SnakeGame extends Game {
	public static final float WORLD_WIDTH = 20f;
	public static final float WORLD_HEIGHT = 20f;
	public static final float PPM = 32f;
	private SpriteBatch batch;

	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new MenuScreen());
		// setScreen(new PlayScreen());
		// setScreen(new GameOverScreen());
	}

	@Override
	public void dispose () {
		batch.dispose();
	}

	public SpriteBatch getBatch() {
		return batch;
	}
}
