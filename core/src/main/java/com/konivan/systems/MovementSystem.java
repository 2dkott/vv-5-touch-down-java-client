package com.konivan.systems;

import com.artemis.annotations.All;
import com.artemis.systems.IteratingSystem;
import com.konivan.component.MainShipComponent;


@All(MainShipComponent.class)
public class MovementSystem extends IteratingSystem {


    @Override
    protected void process(int entityId) {

    }
}
