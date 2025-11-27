package com.konivan.scripts;

import com.artemis.ComponentMapper;
import com.badlogic.gdx.physics.box2d.Body;
import games.rednblack.editor.renderer.components.physics.PhysicsBodyComponent;
import games.rednblack.editor.renderer.scripts.BasicScript;
import lombok.Setter;

public class ShipMovement extends BasicScript {

	protected ComponentMapper<PhysicsBodyComponent> physicMapper;

	@Setter
	private float brakeForce = 0f;
	Body body;

	@Override
	public void init(int entity) {
		super.init(entity);
		body = physicMapper.get(entity).body;

	}

	@Override
	public void act(float delta) {

		body.applyForceToCenter(body.getLinearVelocity().scl(brakeForce * -10), true);
	}

	@Override
	public void dispose() {

	}
}
