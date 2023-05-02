package com.potter;

import com.badlogic.gdx.scenes.scene2d.Group;

import static com.potter.Graphic.ICON_HEIGHT;

public class CounterGroup extends Group {
    void addCounter(CounterActor counterActor){
        counterActor.setPosition(0, getChildren().size*ICON_HEIGHT);
        addActor(counterActor);
    }
}


// przeniesienie plynnie aktora z jednej do drugiej grupy
// po drodze zmienic wartosc boolean
// ponowanie wykorzystac deckGroup do kart zagranych
// jak zagramy wszystkie karty i DeckGroup lewy jest pusty to wtasowujemy wszyskirgo z prawego do lewego