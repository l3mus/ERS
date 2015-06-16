package com.ers.game.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.ers.game.gameobjects.Card;
import com.ers.game.gameobjects.Hand;

/**
 * Created by clemus on 6/12/2015.
 */
public class InputHandler implements InputProcessor {
    Hand gameHand;
    Hand stack;
    Sprite sprite;
    Card card;
    public InputHandler(Hand gameHand, Hand stack) {

        this.gameHand = gameHand;
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
        Gdx.app.log("Sprite","Touched Down");

        //Check if card on top has been touched
        if(gameHand.getCount() > 0) {
            card = gameHand.getCard(0);
            Gdx.app.log("Sprite",card.toString());
            sprite = card.getCurrFace();
            if (sprite.getBoundingRectangle().contains(screenX, screenY)) {
                card.onClick();
                sprite.setPosition(200, 200);
                stack.addCard(card);
                gameHand.removeCard(card);
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
