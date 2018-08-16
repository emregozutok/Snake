package com.mre.game.snake.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.mre.game.snake.SnakeGame;
import com.mre.game.snake.screen.PlayScreen;

public class Snake {

    private enum DIRECTON {LEFT, RIGHT, UP, DOWN}

    private DIRECTON currentDir;
    private static final float speed = 5f;

    private final PlayScreen screen;

    private Array<Sprite> body;
    private Texture texture;
    private float timeToMove;

    public Snake(Texture texture, PlayScreen screen) {
        this.screen = screen;
        this.texture = texture;
        body = new Array<Sprite>();
        Sprite sprite = new Sprite(texture);
        sprite.setBounds(0, 0, 32 / SnakeGame.PPM, 32 / SnakeGame.PPM);
        body.add(sprite);
        sprite = new Sprite(texture);
        sprite.setBounds(-1, 0, 32 / SnakeGame.PPM, 32 / SnakeGame.PPM);
        body.add(sprite);
        sprite = new Sprite(texture);
        sprite.setBounds(-2, 0, 32 / SnakeGame.PPM, 32 / SnakeGame.PPM);
        body.add(sprite);
        currentDir = DIRECTON.RIGHT;
        timeToMove = 0;
    }

    public void draw(SpriteBatch batch) {
        for (Sprite sp : body) {
            sp.draw(batch);
        }
    }

    public void update(float dt) {
        timeToMove += dt * speed;
        if (timeToMove > 1) {
            timeToMove = 0;

            float coefficientX = 0;
            float coefficientY = 0;
            switch (currentDir) {
                case LEFT:
                    coefficientX = -1f;
                    break;
                case RIGHT:
                    coefficientX = 1f;
                    break;
                case UP:
                    coefficientY = 1f;
                    break;
                case DOWN:
                    coefficientY = -1f;
                    break;
            }

            for (int i = body.size - 1; i > 0 ; i--) {
                Rectangle prevPos = body.get(i-1).getBoundingRectangle();
                Sprite sp = body.get(i);
                Rectangle pos = sp.getBoundingRectangle();
                pos.x = prevPos.x;
                pos.y = prevPos.y;
                sp.setPosition(pos.x, pos.y);
            }

            Sprite head = body.get(0);
            Rectangle headPos = head.getBoundingRectangle();
            headPos.x += 32 / SnakeGame.PPM * coefficientX;
            if (headPos.x + headPos.width > screen.getViewport().getWorldWidth() * 0.5f) {
                headPos.x = -screen.getViewport().getWorldWidth() * 0.5f;
            }
            else if (headPos.x < -screen.getViewport().getWorldWidth() * 0.5f) {
                headPos.x = screen.getViewport().getWorldWidth() * 0.5f - headPos.width;
            }
            headPos.y += 32 / SnakeGame.PPM * coefficientY;
            if (headPos.y + headPos.height > screen.getViewport().getWorldHeight() * 0.5f) {
                headPos.y = -screen.getViewport().getWorldWidth() * 0.5f;
            }
            else if (headPos.y < -screen.getViewport().getWorldHeight() * 0.5f) {
                headPos.y = screen.getViewport().getWorldWidth() * 0.5f - headPos.height;
            }
            head.setPosition(headPos.x, headPos.y);
        }
    }

    public void moveLeft() {
        if (currentDir != DIRECTON.RIGHT) {
            currentDir = DIRECTON.LEFT;
        }
    }

    public void moveRight() {
        if (currentDir != DIRECTON.LEFT) {
            currentDir = DIRECTON.RIGHT;
        }
    }

    public void moveUp() {
        if (currentDir != DIRECTON.DOWN) {
            currentDir = DIRECTON.UP;
        }
    }

    public void moveDown() {
        if (currentDir != DIRECTON.UP) {
            currentDir = DIRECTON.DOWN;
        }
    }

    public boolean checkAppleCollision(Rectangle appleRect) {
        Rectangle head = body.get(0).getBoundingRectangle();
        return (head.overlaps(appleRect));
    }

    public boolean collidesWith(Rectangle rect) {
        for (Sprite sp : body) {
            if (sp.getBoundingRectangle().overlaps(rect)) {
                return true;
            }
        }
        return false;
    }

    public boolean collidesWithSelf() {
        Rectangle head = body.get(0).getBoundingRectangle();
        for (int i = 1; i < body.size; i++) {
            Sprite sp = body.get(i);
            if (sp.getBoundingRectangle().overlaps(head)) {
                return true;
            }
        }
        return false;
    }

    public void grow() {
        Rectangle lastPartRect = body.get(body.size - 1).getBoundingRectangle();

        float x = 0;
        float y = 0;
        switch (currentDir) {
            case LEFT:
                x = 1f;
                break;
            case RIGHT:
                x = -1f;
                break;
            case UP:
                y = -1f;
                break;
            case DOWN:
                y = 1f;
                break;
        }
        Sprite sprite = new Sprite(texture);
        sprite.setBounds(lastPartRect.x + x, lastPartRect.y + y, 32 / SnakeGame.PPM, 32 / SnakeGame.PPM);
        body.add(sprite);
    }
}
