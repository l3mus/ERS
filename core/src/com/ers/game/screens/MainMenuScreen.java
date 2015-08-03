package com.ers.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.ers.game.ERS;
import com.ers.game.helpers.AssetLoader;

/**
 * Created by clemus on 6/10/2015.
 */
public class MainMenuScreen implements Screen {

    final ERS game;

    private Stage stage = new Stage();
    private Table table = new Table();

    private Skin skin;


    //On Init
    private BitmapFont buttonFont; //16dp == 12pt

    private TextButton buttonPlay, buttonExit;
    private Label title;

    public MainMenuScreen(final ERS gam) {
        game = gam;

    }

    @Override
    public void show() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("data/unbom.ttf"));
        buttonFont = AssetLoader.createFont(generator, 50);

        skin = new Skin( );
        skin.add("default-font",buttonFont, BitmapFont.class);
        skin.addRegions(new TextureAtlas(Gdx.files.internal("data/uiskin.atlas")));
        skin.load(Gdx.files.internal("data/uiskin.json"));
        /*
        TextButton.TextButtonStyle textButtonStyle = ();
        textButtonStyle.up = skin.newDrawable("default-round");
        textButtonStyle.down = skin.newDrawable("default-round-down");
        textButtonStyle.font = buttonFont;
        skin.add("default", textButtonStyle);*/

        buttonPlay = new TextButton("Play",skin);
        buttonExit = new TextButton("Exit",skin);
        title = new Label("ERS",skin);

        buttonPlay.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //Same way we moved here from the Splash Screen
                //We set it to new Splash because we got no other screens
                //otherwise you put the screen there where you want to go
                game.setScreen(new GameScreen(game));
            }
        });
        buttonExit.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
                // or System.exit(0);
            }
        });
        //The elemetns are displayed in the order you add them.
        //The first appear on top, the last at the bottom.
        table.add(title).padBottom(40).row();
        table.add(buttonPlay).size(400,200).padBottom(20).row();
        table.add(buttonExit).size(400,200).padBottom(20).row();

        table.setFillParent(true);
        stage.addActor(table);

        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
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
        dispose();
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }

}
