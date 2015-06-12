package com.ers.game.screens;

import com.ers.game.ERS;
import com.ers.game.gameworld.GameRenderer;
import com.ers.game.gameworld.GameWorld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.ers.game.helpers.InputHandler;


/**
 * Created by clemus on 6/11/2015.
 */
public class GameScreen implements Screen {
    final ERS game;

    private GameWorld world;
    private GameRenderer renderer;

    private float runTime;
    public GameScreen(final ERS game) {
        this.game = game;

        world = new GameWorld();
        renderer = new GameRenderer(world);


       // Gdx.input.setInputProcessor(new InputHandler(world.getHand()));
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        // Sets a Color to Fill the Screen with (RGB = 10, 15, 230), Opacity of 1 (100%)
        Gdx.gl.glClearColor(10/255.0f, 15/255.0f, 230/255.0f, 1f);
        // Fills the screen with the selected color
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // Covert Frame rate to String, print it
        Gdx.app.log("GameScreen FPS", (1/delta) + "");

        runTime += delta;
        world.update(delta);
        renderer.render(runTime);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
