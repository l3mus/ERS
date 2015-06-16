package com.ers.game.gameworld;

import com.badlogic.gdx.Gdx;
import com.ers.game.gameobjects.Card;
import com.ers.game.gameobjects.Deck;
import com.ers.game.gameobjects.Hand;
import com.ers.game.helpers.InputHandler;

/**
 * Created by clemus on 6/11/2015.
 * helper class of GameScreen
 * Responsible for updating game objects
 */
public class GameWorld {
    private Deck gameDeck;
    private Hand playerHand, stack;

    public GameWorld() {
        gameDeck = new Deck();
        playerHand = new Hand();
        stack = new Hand();
        while(gameDeck.cardsLeft() > 0){
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
     * Returns the players hand
     *
     * @return playerHand the players hand
     */
    public Hand getHand(){
        return playerHand;
    }

    /**
     * Returns the stack, the cards in the middle
     *
     * @return stack the stack of cards in the middle
     */
    public Hand getStack(){
        return stack;
    }
}
