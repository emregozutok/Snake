package com.mre.game.snake.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mre.game.snake.SnakeGame;

public class GameOverScreen extends ScreenAdapter {
    private Stage stage;

    public GameOverScreen() {
        stage = new Stage();
        Table table = new Table();
        table.center();
        table.setFillParent(true);
        Label.LabelStyle style = new Label.LabelStyle(new BitmapFont(), Color.WHITE);
        Label gameOverLabel = new Label("GAME OVER", style);
        table.add(gameOverLabel);

        table.row();
        Label replayLabel = new Label("Click to Play Again", style);
        table.add(replayLabel).padTop(10f);
        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        if (Gdx.input.justTouched()) {
            ((SnakeGame) Gdx.app.getApplicationListener()).setScreen(new PlayScreen());
            dispose();
        }
        Gdx.gl.glClearColor(Color.BLACK.r, Color.BLACK.g, Color.BLACK.b, Color.BLACK.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
