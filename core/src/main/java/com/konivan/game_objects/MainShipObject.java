package com.konivan.game_objects;

import com.konivan.component.MainShipComponent;
import com.konivan.scripts.ShipMovement;
import games.rednblack.editor.renderer.utils.ItemWrapper;

public class MainShipObject extends GameObject {

    @Override
    public void setup(ItemWrapper characterItem) {
        baseSetup(characterItem);
        getComponents().add(MainShipComponent.class);
        getCharacterItem().addScript(ShipMovement.class);
    }

    @Override
    public String getName() {
        return "main-ship-object";
    }


}
