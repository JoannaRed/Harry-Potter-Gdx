package com.potter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;

import static com.potter.Graphic.*;

public class CounterActor extends Actor {
    private Texture texture;
    private BitmapFont bitmapFont;

    public CounterActor(String textureName, BitmapFont bitmapFont) {
        this.texture = new Texture(Gdx.files.internal(textureName));
        setBounds(0,0, ICON_WIDTH, ICON_HEIGHT);
        this.bitmapFont = bitmapFont;
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
        batch.draw(texture, getX(), getY(), getOriginX(), getOriginY(),
                getWidth(), getHeight(), getScaleX(), getScaleY(),
                getRotation(), 0, 0, texture.getWidth(), texture.getHeight(),
                false, false);
        // creating counter numbers
                bitmapFont.draw(batch, "0",  getX() + ICON_WIDTH*0.4f, getY() + ICON_HEIGHT*0.6f);
        batch.setColor(Color.WHITE);

    }


}
