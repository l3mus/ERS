package com.ers.game.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
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
    private Player[] players;
    private Hand stack;
    private Stage stage;

    private BitmapFont scoreFont;
    private StringBuilder stringBuilder;
    private OrthographicCamera cam;
    private ShapeRenderer shapeRenderer;

    public GameRenderer(GameWorld world)
    {
        this.world = world;

        cam = new OrthographicCamera();
        cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        batch = new SpriteBatch();
        batch.setProjectionMatrix(cam.combined);
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);

        initGameObjects();
    }
    private void initGameObjects() {
        stage = world.getStage();
        players = world.getPlayers();
        stack = world.getStack();
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("data/unbom.ttf"));

        scoreFont = AssetLoader.createFont(generator, 50);
        stringBuilder = new StringBuilder();
        shapeRenderer = new ShapeRenderer();
    }
    public void render(float runTime){

        Card card = new Card(); //Used to get current card
        Gdx.app.log("GameRenderer", "render");

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        /*shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        for(Player player : players){
            for(int i = 0; i < player.getHand().getCount(); i++) {
                card = player.getHand().getCard( i );
                shapeRenderer.setColor(Color.RED);
                shapeRenderer.rect(card.getCurrFace().getBoundingRectangle().x,
                        card.getCurrFace().getBoundingRectangle().y, card.getCurrFace().getBoundingRectangle().getWidth(),
                        card.getCurrFace().getBoundingRectangle().getHeight());
            }
        }
        shapeRenderer.end();*/
        batch.begin();
        for(Player player : players){
            for(int i = 0; i < player.getHand().getCount(); i++) {
                card = player.getHand().getCard(i);
                card.getCurrFace().draw(batch);

            }
            stringBuilder.delete(0, stringBuilder.length());
            scoreFont.draw(batch,stringBuilder.append(player.getHand().getCount()).toString(),
                       player.getPosX()+100, player.getPosY());
        }
        for(int i = 0; i < stack.getCount(); i++){
            card = stack.getCard(i);
            card.getCurrFace().draw(batch);
        }
        stringBuilder.delete(0, stringBuilder.length());
        scoreFont.draw(batch,stringBuilder.append(stack.getCount()).toString(),
                Gdx.graphics.getWidth()/2+  300,  Gdx.graphics.getHeight()/2 );
        stage.draw();
        //batch.draw(AssetLoader.animation.getKeyFrame(runTime), 200, 200, 288, 376);
        batch.end();

    }
}
