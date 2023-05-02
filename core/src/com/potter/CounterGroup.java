package com.potter;

import com.badlogic.gdx.scenes.scene2d.Group;

import static com.potter.Graphic.ICON_HEIGHT;

public class CounterGroup extends Group {
    void addCounter(CounterActor counterActor){
        counterActor.setPosition(0, getChildren().size*ICON_HEIGHT);
        addActor(counterActor);
    }
}
