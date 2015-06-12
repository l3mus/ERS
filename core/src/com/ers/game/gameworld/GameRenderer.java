package com.ers.game.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.ers.game.gameobjects.Hand;
import com.ers.game.helpers.AssetLoader;

/**
 * Created by clemus on 6/11/2015.
 * helper class for GameScreen
 * Responsible for rendering game objects
 */
public class GameRenderer {
    private GameWorld world;

    private SpriteBatch batch;

    private AtlasRegion region;
    public GameRenderer(GameWorld world)
    {
        this.world = world;

        batch = new SpriteBatch();
    }

    public void render(float runTime){
        Hand gameHand = world.getHand();
        Gdx.app.log("GameRenderer", "render");

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(Gdx.input.isTouched())
        {

        }
        batch.begin();

        //If there cards still in the hand show the face down texture
        if(gameHand.getCount() > 0){
            batch.draw(AssetLoader.faceDown.getTexture(), Gdx.graphics.getWidth()/2, 0);
        }
        batch.draw(AssetLoader.animation.getKeyFrame(runTime), 200, 200, 288, 376);

        batch.end();
    }
}
