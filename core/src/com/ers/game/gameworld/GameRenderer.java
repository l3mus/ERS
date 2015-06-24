package com.ers.game.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.ers.game.gameobjects.Card;
import com.ers.game.gameobjects.Hand;
import com.ers.game.gameobjects.Player;
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
    Player[] players;
    Hand stack;
    public GameRenderer(GameWorld world)
    {
        this.world = world;

        initGameObjects();
        batch = new SpriteBatch();
    }
    private void initGameObjects() {
        players = world.getPlayers();
        stack = world.getStack();
    }
    public void render(float runTime){

        Card card; //Used to get current card
        Gdx.app.log("GameRenderer", "render");

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        for(Player player : players){
            for(int i = 0; i < player.getHand().getCount(); i++) {
                card = player.getHand().getCard(i);
                Gdx.app.log("Hand", card.toString());
                card.getCurrFace().draw(batch);
            }
        }

        for(int i = 0; i < stack.getCount(); i++){
            card = stack.getCard(i);
            card.getCurrFace().draw(batch);
        }
        //batch.draw(AssetLoader.animation.getKeyFrame(runTime), 200, 200, 288, 376);

        batch.end();
    }
}
