package com.konivan;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.konivan.scenes.BaseScene;
import com.konivan.scenes.TestScene;

/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all
 * platforms.
 */
public class Main extends ApplicationAdapter {

    private GameService gameService;
    BaseScene baseScene;

    @Override
    public void create() {
        gameService = GameService.getInstance();
        baseScene = new TestScene();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameService.getRender().getViewport().apply();
        gameService.getEngine().process();

        baseScene.render();
    }

    @Override
    public void resize(int width, int height) {
        gameService.getRender().getViewport().update(width, height);

        if (width != 0 && height != 0) gameService.getSceneLoader().resize(width, height);
    }

    @Override
    public void dispose() {
        gameService.getAssetManager().dispose();
        gameService.getSceneLoader().dispose();
    }
}
