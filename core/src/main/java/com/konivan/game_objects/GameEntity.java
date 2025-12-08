package com.konivan.game_objects;

import java.util.ArrayList;
import java.util.List;

import com.artemis.PooledComponent;

import games.rednblack.editor.renderer.components.MainItemComponent;
import games.rednblack.editor.renderer.scripts.BasicScript;
import games.rednblack.editor.renderer.utils.ItemWrapper;
import lombok.Getter;

public class GameEntity {

	@Getter
	private final List<Class<? extends PooledComponent>> components = new ArrayList<>();

	@Getter
	private final List<BasicScript> scripts = new ArrayList<>();

    @Getter
    protected int entity;

    @Getter
    private final String name;

    @Getter
    private final ItemWrapper itemWrapper;

    public GameEntity(ItemWrapper itemWrapper){
    	this.itemWrapper = itemWrapper;
        this.entity = itemWrapper.getEntity();
        this.name = itemWrapper.getComponent(MainItemComponent.class).itemIdentifier;
    }

    public <T extends PooledComponent> T getComponent(Class<T> clazz){
        return itemWrapper.getComponent(clazz);
    }

    public void addResource(int entity) {
        itemWrapper.addChild(itemWrapper.getEntity());
    }

}
