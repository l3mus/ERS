package com.ers.game.gameworld;

import com.badlogic.gdx.Gdx;
import com.ers.game.gameobjects.Deck;
import com.ers.game.gameobjects.Hand;

/**
 * Created by clemus on 6/11/2015.
 * helper class of GameScreen
 * Responsible for updating game objects
 */
public class GameWorld {
    private Deck gameDeck;
    private Hand playerHand;

    public GameWorld() {
        gameDeck = new Deck();

        for(int i = 0; i < gameDeck.cardsLeft(); i++){
            playerHand.addCard(gameDeck.dealCard());
        }

    }

    public void update(float update){
        Gdx.app.log("GameWorld","update");
    }

    /**
     * Returns the game deck
     *
     * @return Deck the game deck
     */
    public Deck getDeck(){
        return gameDeck;
    }
    /**
     * Returns the game deck
     *
     * @return Deck the game deck
     */
    public Hand getHand(){
        return playerHand;
    }
}
