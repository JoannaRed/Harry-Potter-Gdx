package com.potter;

import com.badlogic.gdx.scenes.scene2d.Group;

import javax.smartcardio.Card;

public class DeckGroup extends Group {
    void addCard (CardActor cardActor){
        addActor(cardActor);
    }
}
