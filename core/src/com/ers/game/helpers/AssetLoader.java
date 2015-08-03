package com.ers.game.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

/**
 * Created by clemus on 6/12/2015.
 */
public class AssetLoader {

    public static Texture cardTexture;
    public static TextureAtlas cardTextureAtlas;
    public static TextureAtlas uiAtlas;

    public static AtlasRegion faceDown;

    public static Animation animation;
    public static Texture slapButtonTexture;
    public static void load(){
        cardTexture = new Texture(Gdx.files.internal("data/cards.png"));
        cardTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        cardTextureAtlas = new TextureAtlas(Gdx.files.internal("data/cards.atlas"));
        uiAtlas = new TextureAtlas(Gdx.files.internal("data/uiskin.atlas"));

        faceDown = cardTextureAtlas.findRegion("b1fv");
        animation = new Animation(1/5f,cardTextureAtlas.getRegions());
        animation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

        slapButtonTexture = new Texture(Gdx.files.internal("data/button.png"));

    }

    public static void dispose(){
        cardTexture.dispose();
        cardTextureAtlas.dispose();
    }
    public static BitmapFont createFont(FreeTypeFontGenerator generator, float dp)
    {
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        int fontSize = (int)(dp * Gdx.graphics.getDensity());
        parameter.size = fontSize;

        return generator.generateFont(parameter);
    }
}
