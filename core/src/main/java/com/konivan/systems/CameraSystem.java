package com.konivan.systems;

import com.artemis.ComponentMapper;
import com.artemis.annotations.All;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;

import java.util.Objects;

import games.rednblack.editor.renderer.components.TransformComponent;
import games.rednblack.editor.renderer.components.ViewPortComponent;

@All(ViewPortComponent.class)
public class CameraSystem extends IteratingSystem {

	protected ComponentMapper<TransformComponent> transformMapper;

	private Vector3 mVector3 = new Vector3();

	private final Camera camera;

	private TransformComponent transformComponent;

	public CameraSystem(Camera camera) {

		this.camera = camera;
	}

	@Override
	protected void process(int entity) {

		if (!Objects.isNull(transformComponent)) {
			mVector3.set(transformComponent.x+transformComponent.scaleX/2f, transformComponent.y+transformComponent.scaleY, 0);
			camera.position.lerp(mVector3, 0.1f);
		}
	}

	public void setFocus(int focusObject) {
		transformComponent = transformMapper.get(focusObject);
	}
}
