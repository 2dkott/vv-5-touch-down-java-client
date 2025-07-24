package com.konivan.systems;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import games.rednblack.editor.renderer.SceneConfiguration;
import lombok.Getter;

@Getter
public class GameRender {

	private final float screenHeight;
	private final float screenWidth;
	private final Camera camera;
	private final Viewport viewport;
	private final CameraSystem cameraSystem;

	public GameRender(SceneConfiguration config) {

		if (Gdx.app.getType().equals(Application.ApplicationType.Desktop)) {
			screenWidth = 14;
			screenHeight = 31;
		} else {
			screenWidth = (float) Gdx.graphics.getWidth() / 100;
			screenHeight = (float) Gdx.graphics.getHeight() / 100;
		}

		camera = new OrthographicCamera(screenWidth, screenHeight);
		viewport = new ExtendViewport(2, 4, camera);

		cameraSystem = new CameraSystem(camera);
		config.addSystem(cameraSystem);
	}
}
