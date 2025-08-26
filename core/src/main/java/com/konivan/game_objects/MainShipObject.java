package com.konivan.game_objects;

import com.konivan.component.MainShipComponent;

public class MainShipObject extends GameObject {

	private static final String NAME = "main-ship-object";

	public MainShipObject() {
		super(NAME);
		getComponents().add(MainShipComponent.class);
	}

}
