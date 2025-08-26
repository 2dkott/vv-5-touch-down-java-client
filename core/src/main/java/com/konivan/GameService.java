package com.konivan;

import com.artemis.World;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.konivan.systems.CameraSystem;
import com.konivan.systems.GameRender;

import java.util.Objects;

import games.rednblack.editor.renderer.ExternalTypesConfiguration;
import games.rednblack.editor.renderer.SceneConfiguration;
import games.rednblack.editor.renderer.SceneLoader;
import games.rednblack.editor.renderer.resources.AsyncResourceManager;
import games.rednblack.editor.renderer.resources.ResourceManagerLoader;
import games.rednblack.editor.renderer.systems.PhysicsSystem;
import games.rednblack.editor.renderer.utils.ItemWrapper;
import lombok.Getter;

@Getter
public class GameService {

	private final AssetManager assetManager;

	private final SceneLoader sceneLoader;

	private final AsyncResourceManager asyncResourceManager;

	private final World engine;

	private final ItemWrapper root;

	private final GameRender render;

	private static GameService INSTANCE;

	private GameService() {
		assetManager = new AssetManager();

		ExternalTypesConfiguration externalItemTypes = new ExternalTypesConfiguration();

		assetManager.setLoader(AsyncResourceManager.class,
				new ResourceManagerLoader(externalItemTypes, assetManager.getFileHandleResolver()));
		assetManager.load("project.dt", AsyncResourceManager.class);
		assetManager.finishLoading();

		asyncResourceManager = assetManager.get("project.dt", AsyncResourceManager.class);
		SceneConfiguration config = new SceneConfiguration();

		render = new GameRender(config);

		config.setExternalItemTypes(externalItemTypes);
		config.setResourceRetriever(asyncResourceManager);

		config.addSystem(new PhysicsSystem());

		sceneLoader = new SceneLoader(config);
		engine = sceneLoader.getEngine();

		root = new ItemWrapper(sceneLoader.getRoot(), engine);
	}

	public static GameService getInstance() {
		if (Objects.isNull(INSTANCE)) {
			INSTANCE = new GameService();
		}

		return INSTANCE;
	}
}
