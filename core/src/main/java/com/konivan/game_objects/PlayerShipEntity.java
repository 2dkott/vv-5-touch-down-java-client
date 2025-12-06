package com.konivan.game_objects;

import com.konivan.component.MainShipComponent;
import com.konivan.scripts.ShipMovementScript;
import games.rednblack.editor.renderer.utils.ItemWrapper;
import lombok.Getter;

public class PlayerShipEntity extends GameEntity {

    @Getter
    ShipMovementScript  movementScript =  new ShipMovementScript();

    public PlayerShipEntity(ItemWrapper itemWrapper) {
        super(itemWrapper);
        getComponents().add(MainShipComponent.class);
        getScripts().add(movementScript);
    }

    public MainShipComponent getmainShipComponent() {
        return getComponent(MainShipComponent.class);
    }
}
