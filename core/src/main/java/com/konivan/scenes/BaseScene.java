package com.konivan.scenes;

import com.badlogic.gdx.math.Vector2;
import com.konivan.GameService;
import com.konivan.exception.NoEntityFondException;
import com.konivan.exception.NoResourceFondException;
import com.konivan.game_objects.GameEntity;
import com.konivan.game_objects.GameObject;

import games.rednblack.editor.renderer.components.DimensionsComponent;
import games.rednblack.editor.renderer.components.NodeComponent;
import games.rednblack.editor.renderer.components.ZIndexComponent;
import games.rednblack.editor.renderer.data.CompositeItemVO;
import games.rednblack.editor.renderer.utils.ComponentRetriever;
import games.rednblack.editor.renderer.utils.ItemWrapper;
import lombok.Getter;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public abstract class BaseScene {
	protected static final GameService gameService = GameService.getInstance();

	@Getter
	private ItemWrapper root;

	public BaseScene(String sceneName) {
		gameService.getSceneLoader().loadScene(sceneName, gameService.getRender().getViewport());
		root = new ItemWrapper(gameService.getSceneLoader().getRoot(), gameService.getSceneLoader().getEngine());
	}

    public void addResoucrecFromLibraryToParent(ItemWrapper parent, String resourceName) {

        CompositeItemVO resourceVO = gameService.getSceneLoader().loadVoFromLibrary(resourceName);

        resourceVO.layerName = parent.getComponent(ZIndexComponent.class).getLayerName();
        resourceVO.x = parent.getComponent(DimensionsComponent.class).width/2 - resourceVO.width/2;
        resourceVO.y = 0;

        int resourceEntity = gameService.getSceneLoader().getEntityFactory().createEntity(parent.getEntity(), resourceVO);
        gameService.getSceneLoader().getEntityFactory().initAllChildren(resourceEntity, resourceVO);

        NodeComponent nodeComponent = ComponentRetriever.get(parent.getEntity(), NodeComponent.class, gameService.getEngine());
        if (nodeComponent != null) {
            nodeComponent.children.add(resourceEntity);
        }
    }

	public <T extends GameObject> T loadToScene(Class<T> gameObjectClass, Vector2 position, String layer) {
		try {
			Constructor<T> constructor = gameObjectClass.getConstructor();
			T gameObject = constructor.newInstance();
			int entity = gameService.getSceneLoader().loadFromLibrary(gameObject.getName(), layer, position.x,
					position.y);
			if (entity < 1) {
				gameObject = null;
			} else {
				ItemWrapper characterItem = new ItemWrapper(entity, gameService.getEngine());
				gameObject.init(characterItem);
			}
			return gameObject;
		} catch (InvocationTargetException | InstantiationException | IllegalAccessException
				| NoSuchMethodException e) {
			return null;
		}
	}

	public ItemWrapper loadResourceToScene(String resourceName, String layer, Vector2 position)
			throws NoResourceFondException {

        int entity = gameService.getSceneLoader().loadFromLibrary(resourceName, layer, position.x, position.y);

        if (entity >= 1) {
			return new ItemWrapper(entity, gameService.getEngine());
		}

		throw new NoResourceFondException(resourceName);
	}

	public <T extends GameObject> T setupFromScene(Class<T> gameObjectClass) {
		try {
			Constructor<T> constructor = gameObjectClass.getConstructor();
			T gameObject = constructor.newInstance();
			ItemWrapper characterItem = root.getChild(gameObject.getName());
			if (characterItem.getEntity() < 1) {
				gameObject = null;
			} else {
				gameObject.init(characterItem);
			}
			return gameObject;
		} catch (InvocationTargetException | InstantiationException | IllegalAccessException
				| NoSuchMethodException e) {
			return null;
		}
	}

	public <T extends GameEntity> T buildFromScene(Class<T> gameEntityClass, String nameId)
			throws NoEntityFondException {

		ItemWrapper itemWrapper = root.getChild(nameId);

		if (itemWrapper.getEntity() >= 1) {
			try {

				Constructor<T> constructor = gameEntityClass.getDeclaredConstructor(ItemWrapper.class);

				T gameEntity = constructor.newInstance(itemWrapper);

				gameEntity.getComponents().forEach(component -> ComponentRetriever.create(gameEntity.getEntity(),
						component, gameService.getEngine()));

				gameEntity.getScripts().forEach(itemWrapper::addScript);

				return gameEntity;

			} catch (InvocationTargetException | InstantiationException | IllegalAccessException
					| NoSuchMethodException e) {
				return null;
			}

		}

		throw new NoEntityFondException(nameId);
	}

	public GameEntity buildFromScene(String nameId) throws NoEntityFondException {

		ItemWrapper itemWrapper = root.getChild(nameId);

		if (itemWrapper.getEntity() >= 1) {

			return new GameEntity(itemWrapper);

		}

		throw new NoEntityFondException(nameId);
	}

	public abstract void render();
}
