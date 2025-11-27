package com.konivan.game_objects;

import com.konivan.component.MainShipComponent;
import com.konivan.scripts.ShipMovement;
import games.rednblack.editor.renderer.utils.ItemWrapper;
import lombok.Getter;

public class MainShipObject extends GameObject {

	@Getter
	private final ShipMovement shipMovement = new ShipMovement();

	@Override
	public void setup(ItemWrapper characterItem) {
		baseSetup(characterItem);
		getComponents().add(MainShipComponent.class);
		getCharacterItem().addScript(shipMovement);
	}

	@Override
	public String getName() {
		return "main-ship-object";
	}

}
