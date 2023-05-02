package com.potter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;

import static com.potter.Graphic.*;

public class CardActor extends Actor {
    private Texture faceUpTexture;
    private Texture faceDownTexture;
    private DragCardListener dcl = new DragCardListener();
    private boolean faceUp = false;

    public CardActor() {
        this.faceUpTexture = new Texture(Gdx.files.internal("card.png"));
        this.faceDownTexture = new Texture(Gdx.files.internal("card_back.png"));
        setBounds(0,0, CARD_WIDTH, CARD_HEIGHT);

        addListener(dcl);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
        // ustawianie tekstury aktywnej karty
        Texture activeTexture;
        if(faceUp){
            activeTexture = faceUpTexture;
        }else {
            activeTexture = faceDownTexture;
        }
        batch.draw(activeTexture, getX(), getY(), getOriginX(), getOriginY(),
                getWidth(), getHeight(), getScaleX(), getScaleY(),
                getRotation(), 0, 0, activeTexture.getWidth(), activeTexture.getHeight(),
                false, false);
        batch.setColor(Color.WHITE);

    }

    class DragCardListener extends DragListener{
        private float deltaX;
        private float deltaY;



        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            deltaX = x;
            deltaY = y;
            return super.touchDown(event, x, y, pointer, button);
        }

        @Override
        public void drag(InputEvent event, float x, float y, int pointer) {
            float newX = getX() + x - deltaX;
            float newY = getY() + y - deltaY;
            setPosition(newX, newY);


        }

        @Override
        public void dragStop(InputEvent event, float x, float y, int pointer) {
            if(getY()>= SCREEN_HEIGHT/4){
                removeListener(dcl);
                // znikanie karty
                //addAction(Actions.fadeOut(1));

                //przesylanie zagranych kart do rogu
                addAction(Actions.moveTo(SCREEN_WIDTH*0.6f , 0, 0.5f, Interpolation.circleIn));
            }
            super.dragStop(event, x, y, pointer);
        }
    }

}
