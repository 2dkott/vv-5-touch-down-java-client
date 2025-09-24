package com.konivan.scenes;

import com.badlogic.gdx.math.Vector2;
import com.konivan.GameService;
import com.konivan.game_objects.GameObject;

import games.rednblack.editor.renderer.utils.ItemWrapper;
import lombok.Getter;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BaseScene {
	protected static final GameService gameService = GameService.getInstance();

	@Getter
	private ItemWrapper root;

	public BaseScene(String sceneName) {
		gameService.getSceneLoader().loadScene(sceneName, gameService.getRender().getViewport());
		root = new ItemWrapper(gameService.getSceneLoader().getRoot(), gameService.getSceneLoader().getEngine());
	}

	public <T extends GameObject> T loadToScene(Class<T> gameObjectClass, Vector2 position, String layer) {
        try {
            Constructor<T> constructor = gameObjectClass.getConstructor();
            T gameObject = constructor.newInstance();
            int entity = gameService.getSceneLoader().loadFromLibrary(gameObject.getName(), layer, position.x, position.y);
            if (entity < 1) {
                gameObject = null;
            } else {
                ItemWrapper characterItem = new ItemWrapper(entity, gameService.getEngine());
                gameObject.setup(characterItem);
            }
            return gameObject;
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            return null;
        }
	}

	public <T extends GameObject> T findInScene(Class<T> gameObjectClass) {
        try {
            Constructor<T> constructor = gameObjectClass.getConstructor();
            T gameObject = constructor.newInstance();
            ItemWrapper characterItem = root.getChild(gameObject.getName());
            if (characterItem.getEntity() < 1) {
                gameObject = null;
            } else {
                gameObject.setup(characterItem);
            }
            return gameObject;
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            return null;
        }
    }
}
