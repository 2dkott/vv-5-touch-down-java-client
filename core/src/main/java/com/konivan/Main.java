package com.konivan;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.konivan.characters.CameraFocusPoint;
import com.konivan.characters.CharacterLoader;

import java.time.chrono.MinguoChronology;

/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms.
 */
public class Main extends ApplicationAdapter {

    private GameService gameService;

    @Override
    public void create() {
        gameService = GameService.getInstance();

        gameService.getSceneLoader().loadScene("TestScene", gameService.getViewport());

        CameraFocusPoint cameraFocusPoint = new CameraFocusPoint();
        CharacterLoader.loadToScene(cameraFocusPoint, new Vector2(0f, 0f), "Default");
        gameService.getCameraSystem().setFocus(cameraFocusPoint.getEntity());
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameService.getViewport().apply();
        gameService.getEngine().process();
    }

    @Override
    public void resize(int width, int height) {
        gameService.getViewport().update(width, height);

        if (width != 0 && height != 0)
            gameService.getSceneLoader().resize(width, height);
    }

    @Override
    public void dispose() {
        gameService.getAssetManager().dispose();
        gameService.getSceneLoader().dispose();
    }
}
