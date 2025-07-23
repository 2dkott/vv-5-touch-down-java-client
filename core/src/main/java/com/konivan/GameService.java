package com.konivan;

import com.artemis.World;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.konivan.systems.CameraSystem;

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

    private final CameraSystem cameraSystem;

    private final Camera camera;

    private final ExtendViewport viewport;

    private float screenHeight;

    private float screenWidth;
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

        config.setExternalItemTypes(externalItemTypes);
        config.setResourceRetriever(asyncResourceManager);

        cameraSystem = new CameraSystem(0, 40, 0, 6);
        config.addSystem(cameraSystem);
        config.addSystem(new PhysicsSystem());

        sceneLoader = new SceneLoader(config);
        engine = sceneLoader.getEngine();

        root = new ItemWrapper(sceneLoader.getRoot(), engine);

        if (Gdx.app.getType().equals(Application.ApplicationType.Desktop)) {
            screenWidth = 500;
            screenHeight = 2000;
        } else {
            screenWidth = Gdx.graphics.getWidth();
            screenHeight = Gdx.graphics.getHeight();
        }

        camera = new OrthographicCamera(screenWidth, screenHeight);
        viewport = new ExtendViewport(0, 5, camera);
    }

    public static GameService getInstance() {
        if(Objects.isNull(INSTANCE)) {
            INSTANCE = new GameService();
        }

        return INSTANCE;
    }
}
