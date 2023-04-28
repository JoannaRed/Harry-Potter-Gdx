package com.potter;

import com.badlogic.gdx.scenes.scene2d.Group;

import static com.potter.Graphic.CARD_WIDTH;

public class HandGroup extends Group {
    void addCard(CardActor cardActor){
        cardActor.setPosition(getChildren().size*CARD_WIDTH, 0);
        addActor(cardActor);


    }
}
