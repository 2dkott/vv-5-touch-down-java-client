package com.konivan.scenes;

import com.badlogic.gdx.math.Vector2;
import com.konivan.GameService;
import com.konivan.game_objects.GameObject;

import games.rednblack.editor.renderer.utils.ItemWrapper;
import lombok.Getter;


public class BaseScene {
    protected static final GameService gameService = GameService.getInstance();

    @Getter
    private ItemWrapper root;
    public BaseScene(String sceneName) {
        gameService.getSceneLoader().loadScene(sceneName, gameService.getRender().getViewport());
        root = new ItemWrapper(gameService.getSceneLoader().getRoot(), gameService.getSceneLoader().getEngine());
    }

    public GameObject loadToScene(GameObject gameObject, Vector2 position, String layer) {
        int entity = gameService.getSceneLoader().loadFromLibrary(gameObject.getName(), layer, position.x, position.y);
        ItemWrapper characterItem = new ItemWrapper(entity, gameService.getEngine());
        return gameObject.addItemWrapper(characterItem);
    }

    public GameObject findInScene(GameObject gameObject) {
        ItemWrapper  characterItem = root.getChild(gameObject.getName());
        return gameObject.addItemWrapper(characterItem);
    }

}
