package com.ers.game.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.ers.game.gameobjects.Card;
import com.ers.game.gameobjects.Hand;
import com.ers.game.gameobjects.Player;

/**
 * Created by clemus on 6/12/2015.
 */
public class InputHandler implements InputProcessor {
    private Player [] players;
    private Hand stack;

    public InputHandler(Player[] players, Hand stack) {

        this.players = players;
        this.stack = stack;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

         //Check if card on top has been touched
         for(Player player : players) {
             if (player.getTurnsLeft() > 0) {
                 player.play(screenX,screenY, stack);
             }
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
