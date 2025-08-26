package com.konivan.game_objects;

import com.artemis.Component;

import java.util.ArrayList;
import java.util.List;

import games.rednblack.editor.renderer.utils.ItemWrapper;
import lombok.Getter;

@Getter
public class GameObject {

	private ItemWrapper characterItem;
	private int entity;
	private final List<Class<? extends Component>> components = new ArrayList<>();
	private final String name;

	protected GameObject(String name) {
		this.name = name;
	}

	public GameObject addItemWrapper(ItemWrapper characterItem) {
		this.characterItem = characterItem;
		this.entity = characterItem.getEntity();
		return this;
	}
}
