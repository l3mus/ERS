package com.ers.game.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.ers.game.gameobjects.Card;
import com.ers.game.gameobjects.Deck;
import com.ers.game.gameobjects.Hand;
import com.ers.game.gameobjects.Player;
import com.ers.game.helpers.AssetLoader;
import com.ers.game.helpers.InputHandler;


/**
 * Created by clemus on 6/11/2015.
 * helper class of GameScreen
 * Responsible for updating game objects
 */
public class GameWorld {
    private Deck gameDeck;
    private Player[]  players;
    private Hand stack;
    private Card card;
    private Stage stage;

    private int playerCount;
    private int currentPlayer;
    private int previousPlayer;
    private int specialPlayer; //This number signifies the player that played a ranked card

    private float playerPosX;
    private float playerPosY;

    public GameWorld() {
        stage = new Stage();
        gameDeck = new Deck();
        card = new Card();
        stack = new Hand();
        playerCount = 2;
        players = new Player[playerCount];
        currentPlayer = 0;
        previousPlayer = 0;
        specialPlayer =  -1; //No current specialPlayer
        playerPosX =  Gdx.graphics.getWidth()/2;
        playerPosY =  0 + card.getCurrFace().getHeight()*2;

        //@todo: Set for two players right now
        for(int i = 0; i < playerCount; i++){
          players[i] = new Player(0, new Hand(), playerPosX, playerPosY);
          playerPosY = Gdx.graphics.getHeight() - card.getCurrFace().getHeight() * 3;
          stage.addActor(players[i].getSlapButton());
        }
        players[0].setTurnsLeft(1); //Give player1 first move

        gameDeck.shuffle();

        //Deal a card to each player until deck is empty
        while(gameDeck.cardsLeft() > 0){
            for(int i = 0; i < playerCount; i++){
                players[i].getHand().addCard(gameDeck.dealCard());
            }
        }
       setHandPositions();
    }

    public void update(float update){
        Gdx.app.log("GameWorld","update");
        if(players[currentPlayer].isPlayed())
        {
            int rank;
            rank = stack.getTopCard().getRank();
            players[currentPlayer].setPlayed(false);

            //Assign the next player a turn based on what is on top of the deck
            if(players[currentPlayer].getTurnsLeft() == 0 && (rank != 11 && rank != 12 && rank != 13 && rank != 1)) {
                setNextCurrentPlayer();
                //Current Player ran out of turns but the last player played a specially ranked card(the specialPlayer gets the cards);
                if(specialPlayer >= 0) {
                    emptyStack(players[currentPlayer]);
                    specialPlayer = -1;
                }
                players[currentPlayer].setTurnsLeft(1);
            }
            checkRank(rank);
        }
        for(Player player : players) {
            if(player.isSlapped())
            {
                checkStack(player);
            }
        }
    }
    public void checkStack(Player player){
        if(stack.getCount() > 1) {
            if (stack.getTopCard().getRank() == stack.getCard(stack.getCount() - 1).getRank()) {
                emptyStack(player);
            }
        }
        player.setSlapped(false);
    }
    public void emptyStack(Player player){
        int stackSize = stack.getCount();
        for (int i = 0; i < stackSize; i++) {

            card = stack.getFirstCard();
            card.setFacedUp(false);
            card.onClick();
            card.getCurrFace().setPosition(player.getPosX(),player.getPosY());
            player.getHand().addCard(card);
            stack.removeCard(card);
        }
    }
    public void checkRank(int rank)
    {
        if(rank == 11 || rank == 12 || rank == 13 || rank == 1) {
            specialPlayer = currentPlayer;
            players[currentPlayer].setTurnsLeft(0);
            setNextCurrentPlayer();
        }
        //If jack is on top
        if(rank == 11 ){
            players[currentPlayer].setTurnsLeft(1);
        }
        //If Queen is on top
        else if(rank == 12){
            players[currentPlayer].setTurnsLeft(2);
        }
        //If King is on top
        else if(rank== 13){
            players[currentPlayer].setTurnsLeft(3);
        }
        //If Ace is on top
        else if(rank == 1){
            players[currentPlayer].setTurnsLeft(4);
        }
    }
    public void setNextCurrentPlayer(){
        previousPlayer = currentPlayer;
        currentPlayer++;
        if(currentPlayer > playerCount -1){
            currentPlayer = 0;
        }
    }

    /**
     * @todo set multiple player cards in different positions more effectively
     */
    public void setHandPositions(){
        for(int i = 0; i < playerCount; i++){
           for(int j = 0; j < players[i].getHand().getCount(); j++){
              players[i].getHand().getCard(j).getCurrFace().setPosition( players[i].getPosX(),  players[i].getPosY());
              players[i].getHand().getCard(j).getCurrFace().getBoundingRectangle();
           }
        }
    }
    /**
     * Returns the game deck
     *
     * @return Deck the game deck
     */
    public Deck getDeck(){ return gameDeck; }
    /**
     * Returns the game deck
     *
     * @return Deck the game deck
     */
    public Player[] getPlayers(){ return players; }
    /**
     * Returns the stack, the cards in the middle
     *
     * @return stack the stack of cards in the middle
     */
    public Hand getStack(){
        return stack;
    }

    public Stage getStage(){ return stage;}
}
