package com.konivan.characters;

import com.badlogic.gdx.math.Vector2;
import com.konivan.GameService;

import games.rednblack.editor.renderer.utils.ItemWrapper;

public class CharacterLoader {

	private static final GameService gameService = GameService.getInstance();

	public static void loadToScene(BaseGameObject gameObject, Vector2 position, String layer) {
		int entity = gameService.getSceneLoader().loadFromLibrary(gameObject.getName(), layer, position.x, position.y);
		ItemWrapper characterItem = new ItemWrapper(entity, gameService.getEngine());
		gameObject.setCharacterItem(characterItem);
		gameObject.setEntity(entity);
	}
}
