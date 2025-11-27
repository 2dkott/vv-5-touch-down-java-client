package com.konivan.scenes;

import com.artemis.Component;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.konivan.game_objects.GameObject;
import com.konivan.game_objects.MainShipObject;

import com.konivan.scripts.ShipMovement;
import com.konivan.stage.LandingHUD;
import com.konivan.static_data.HudSource;
import games.rednblack.editor.renderer.utils.ComponentRetriever;

import java.lang.reflect.InvocationTargetException;

public class TestScene extends BaseScene {

	private final MainShipObject mainShip;
	private final LandingHUD hud;

	public TestScene() {
		super("TestScene");

		this.mainShip = findInScene(MainShipObject.class);
		mainShip.getComponents().forEach(ComponentRetriever::addMapper);

		gameService.getRender().getCameraSystem().setFocus(this.mainShip.getEntity());

		Skin skin = gameService.getAssetManager().get(HudSource.landingHUDSkinPath);
		skin.addRegions(gameService.getAssetManager().get(HudSource.landingHUDAtlasPath));

        hud = new LandingHUD(skin, new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()), gameService.getSceneLoader().getBatch(),
				mainShip.getShipMovement());

		Gdx.input.setInputProcessor(hud);
	}

	@Override
	public void render() {
		hud.act(Gdx.graphics.getDeltaTime());
		hud.draw();
	}

}
