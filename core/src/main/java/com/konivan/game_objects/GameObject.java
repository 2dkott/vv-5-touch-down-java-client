package com.konivan.game_objects;

import com.artemis.Component;

import java.util.ArrayList;
import java.util.List;

import games.rednblack.editor.renderer.utils.ItemWrapper;
import lombok.Getter;

@Getter
public abstract class GameObject {

	private ItemWrapper characterItem;
	private int entity;
	private final List<Class<? extends Component>> components = new ArrayList<>();

	void baseSetup(ItemWrapper characterItem) {
		this.characterItem = characterItem;
		this.entity = characterItem.getEntity();
	}

    abstract public void setup(ItemWrapper characterItem);
    abstract public String getName();


}
