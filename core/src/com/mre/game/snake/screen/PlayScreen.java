package com.mre.game.snake.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mre.game.snake.SnakeGame;
import com.mre.game.snake.entity.Apple;
import com.mre.game.snake.entity.Snake;

public class PlayScreen extends ScreenAdapter {
    private Viewport viewport;
    private OrthographicCamera cam;
    private SnakeGame game;
    private SpriteBatch batch;
    private Snake snake;
    private Texture snakeTexture;
    private Texture appleTexture;
    private Apple apple;

    public PlayScreen() {
        this.game = (SnakeGame) Gdx.app.getApplicationListener();
        this.batch = game.getBatch();

        this.cam = new OrthographicCamera();
        this.viewport = new FitViewport(SnakeGame.WORLD_WIDTH, SnakeGame.WORLD_HEIGHT, cam);

        this.snakeTexture = new Texture("body.png");
        this.snake = new Snake(snakeTexture, this);
        this.appleTexture = new Texture("apple.png");
        Rectangle rect = getRandomWorldRect();
        this.apple = new Apple(appleTexture, rect.x, rect.y);
    }

    private Rectangle getRandomWorldRect() {
        float x = getRandomWorldX();
        float y = getRandomWorldY();

        Rectangle rect = new Rectangle(x, y, 32 / SnakeGame.PPM, 32 / SnakeGame.PPM);
        if (snake.collidesWith(rect)) {
            rect = getRandomWorldRect();
            Gdx.app.log("Collision", "Snake collides with the rect: " + rect.toString());
        }
        return rect;
    }

    private float getRandomWorldX() {
        int x = (int) MathUtils.random(viewport.getWorldWidth() * 0.5f);
        x = x * (MathUtils.random(1) == 0 ?  -1 : 1);
        return x;
    }

    private float getRandomWorldY() {
        int y = (int) MathUtils.random(viewport.getWorldHeight() * 0.5f);
        return y * (MathUtils.random(1) == 0 ?  -1 : 1);
    }

    @Override
    public void render(float delta) {
        input();
        update(delta);
        draw();
    }

    private void input() {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            snake.moveLeft();
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            snake.moveRight();
        } else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            snake.moveUp();
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            snake.moveDown();
        }
    }

    private void update(float dt) {
        snake.update(dt);
        if (snake.collidesWithSelf()) {
            game.setScreen(new GameOverScreen());
            dispose();
        }
        if (snake.checkAppleCollision(apple.getBoundingRectangle())) {
            snake.grow();
            Rectangle rect = getRandomWorldRect();
            apple.setPosition(rect.x, rect.y);
        }
    }

    private void draw() {
        Gdx.gl.glClearColor(0, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(cam.combined);
        batch.begin();
        snake.draw(batch);
        apple.draw(batch);
        batch.end();
    }

    public Viewport getViewport() {
        return viewport;
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void dispose() {
        snakeTexture.dispose();
        appleTexture.dispose();
    }
}
