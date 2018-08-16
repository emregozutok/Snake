package com.mre.game.snake.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mre.game.snake.SnakeGame;

public class DesktopLauncher {
    public static void main(String[] args) {
        LwjglApplicationConfiguration conf = new LwjglApplicationConfiguration();
        conf.width = 640;
        conf.height = 640;
        new LwjglApplication(new SnakeGame(), conf);
    }
}
