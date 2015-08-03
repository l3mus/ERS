package com.ers.game.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.ers.game.helpers.AssetLoader;
import com.ers.game.screens.GameScreen;

/**
 * Created by clemus on 6/23/2015.
 */
public class Player {
    private int turnsLeft; //Is players turn to play
    private boolean played; //Has player placed a card
    private boolean slapped; //Has player slapped
    private float posX;
    private float posY;

    private Hand hand; //The players hand

    private Skin skin;
    private TextButton slapButton;
    //On Init
    private BitmapFont buttonFont; //16dp == 12pt

    /**
     * Constructor. Create a player
     */
    public Player(){
        turnsLeft = 0;
        hand = new Hand();
    }
    /**
     * Card Constructor
     *
     * @param: isTurn
     *  true if it is players turn, false if it is not
     * @param: hand
     *  The player's hand
     */
    public Player(int turnsLeft, Hand hand,float posX, float posY){
        this.turnsLeft = turnsLeft;
        this.hand = hand;
        this.posX = posX;
        this.posY = posY;

       create();
    }
    public void create(){
       /* skin = new Skin( );
        skin.addRegions(new TextureAtlas(Gdx.files.internal("data/button.pack")));
        skin.load(Gdx.files.internal("data/button.json"));
        slapButton = new com.badlogic.gdx.scenes.scene2d.ui.Button(skin);*/

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("data/unbom.ttf"));
        buttonFont = AssetLoader.createFont(generator, 50);
        skin = new Skin( );
        skin.add("default-font",buttonFont, BitmapFont.class);
        skin.addRegions(new TextureAtlas(Gdx.files.internal("data/uiskin.atlas")));
        skin.load(Gdx.files.internal("data/uiskin.json"));
        slapButton = new com.badlogic.gdx.scenes.scene2d.ui.TextButton("Slap", skin);
        slapButton.setPosition(this.posX+ 250,this.posY);

        slapButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //Same way we moved here from the Splash Screen
                //We set it to new Splash because we got no other screens
                //otherwise you put the screen there where you want to go
                slapped = true;
            }
        });
    }
    public void play(float screenX, float screenY, Hand stack)
    {
        if(hand.getCount() != 0) {
            Card card = hand.getFirstCard();
            Sprite sprite = card.getCurrFace();

            float touchX = screenX - sprite.getWidth()/2;
            float touchY = Gdx.graphics.getHeight() - screenY - sprite.getHeight()/2;
            if (sprite.getBoundingRectangle().contains(touchX, touchY)) {
                card.setFacedUp(true);
                card.onClick();
                sprite.setPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
                sprite.getBoundingRectangle();
                stack.addCard(card);
                hand.removeCard(card);
                played = true;
                turnsLeft--;
            }

        }
    }
    public int getTurnsLeft() {
        return turnsLeft;
    }

    public void setTurnsLeft(int turnsLeft) {
        this.turnsLeft = turnsLeft;
    }

    public boolean isPlayed() {
        return played;
    }

    public void setPlayed(boolean played) {
        this.played = played;
    }

    public boolean isSlapped() {
        return slapped;
    }

    public void setSlapped(boolean slapped) {
        this.slapped = slapped;
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public float getPosX() {
        return posX;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public float getPosY() {
        return posY;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }

    public TextButton getSlapButton() {
        return slapButton;
    }
    public void setSlapButton(TextButton slapButton) {
        this.slapButton = slapButton;
    }

}
