package com.ers.game.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by clemus on 6/23/2015.
 */
public class Player {
    private int turnsLeft; //Is players turn to play
    private boolean played; //Has player played
    private float posX;
    private float posY;

    private Hand hand; //The players hand

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
    }
    public void play(float screenX, float screenY, Hand stack)
    {
        if(hand.getCount() != 0) {
            Card card = hand.getCard(0);
            Sprite sprite = card.getCurrFace();
            if (sprite.getBoundingRectangle().contains(screenX, screenY)) {
                card.onClick();
                sprite.setPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
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
}
