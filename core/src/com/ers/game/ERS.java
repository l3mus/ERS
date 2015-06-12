package com.ers.game;

import com.badlogic.gdx.Game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.ers.game.helpers.AssetLoader;
import com.ers.game.screens.MainMenuScreen;

public class ERS extends Game {
	
	@Override
	public void create () {
        AssetLoader.load();
        this.setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render () {
        super.render(); //Important!
	}

    @Override
    public void dispose () {
        super.dispose();
        AssetLoader.dispose();
    }
}
