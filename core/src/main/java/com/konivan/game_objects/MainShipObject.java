package com.konivan.game_objects;

import com.konivan.component.MainShipComponent;
import com.konivan.scripts.ShipMovementScript;
import games.rednblack.editor.renderer.utils.ItemWrapper;
import lombok.Getter;

public class MainShipObject extends GameObject {

	@Getter
	private final ShipMovementScript shipMovementScript = new ShipMovementScript();

	public MainShipObject() {
		super("main-ship-object");
	}

	@Override
    public void init(ItemWrapper characterItem) {
        super.init(characterItem);
        getComponents().add(MainShipComponent.class);
        getCharacterItem().addScript(shipMovementScript);
    }

}
