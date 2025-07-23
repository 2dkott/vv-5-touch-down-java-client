package com.konivan.characters;

import games.rednblack.editor.renderer.utils.ItemWrapper;
import lombok.Data;

@Data
public class BaseGameObject {

	private ItemWrapper characterItem;
	private int entity;

	private final String name;

	public BaseGameObject(String name) {
		this.name = name;
	}
}
