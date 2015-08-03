package com.ers.game.gameobjects;

import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by clemus on 6/26/2015.
 */
public class Button {
    private Sprite currState;
    private boolean isPressed; //faced up mean showing the suit and rank shows

    public Button(Sprite currState, boolean isFacedUp) {
        this.currState = currState;
        this.isPressed = isFacedUp;
    }

    public boolean isPressed() {
        return isPressed;
    }

    public void setPressed(boolean isPressed) {
        this.isPressed = isPressed;
    }

    public Sprite getCurrState() {
        return currState;
    }

    public void setCurrState(Sprite currState) {
        this.currState = currState;
    }

}
