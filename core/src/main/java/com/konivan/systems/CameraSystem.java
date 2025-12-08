package com.konivan.systems;

import com.artemis.ComponentMapper;
import com.artemis.annotations.All;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;

import java.util.Objects;

import games.rednblack.editor.renderer.components.DimensionsComponent;
import games.rednblack.editor.renderer.components.TransformComponent;
import games.rednblack.editor.renderer.components.ViewPortComponent;

@All(ViewPortComponent.class)
public class CameraSystem extends IteratingSystem {

	private ComponentMapper<TransformComponent> transformMapper;
    private ComponentMapper<DimensionsComponent> dimensionMapper;

    private Integer focusEntityId;
    private Camera camera;
	private Vector3 mVector3 = new Vector3();

	private TransformComponent focusTransformComponent;
	private DimensionsComponent focusDimensionsComponent;
    private static final float cameraSpeed = 15.0f;

	public CameraSystem(Camera camera) {
		this.camera = camera;
	}

	@Override
	protected void process(int entity) {

        if (focusTransformComponent != null) {
            float x = focusTransformComponent.x + focusDimensionsComponent.width / 2f;
            float y = focusTransformComponent.y;

            mVector3.set(x, y, camera.position.z);

            float dt = world.getDelta();
            float alpha = 1.0f - (float) Math.exp(-cameraSpeed * dt);
            if (alpha > 1.0f) alpha = 1.0f;

            camera.position.lerp(mVector3, alpha);
        }
	}

	public void setFocus(int focusObject) {
		this.focusEntityId = focusObject;
        focusTransformComponent = transformMapper.get(focusObject);
        focusDimensionsComponent = dimensionMapper.get(focusObject);
	}
}
