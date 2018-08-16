package com.mre.game.snake.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mre.game.snake.SnakeGame;

public class Apple extends Sprite {

    public Apple(Texture texture, float x, float y) {
        super(texture);
        setBounds(x, y, 32 / SnakeGame.PPM, 32 / SnakeGame.PPM);
    }

}
