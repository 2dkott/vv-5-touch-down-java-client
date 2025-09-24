package com.konivan.scripts;

import com.artemis.ComponentMapper;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import games.rednblack.editor.renderer.components.physics.PhysicsBodyComponent;
import games.rednblack.editor.renderer.scripts.BasicScript;

public class ShipMovement extends BasicScript {

	private PhysicsBodyComponent physicsBodyComponent;
	protected ComponentMapper<PhysicsBodyComponent> physicMapper;

	@Override
	public void init(int entity) {
		super.init(entity);
		physicsBodyComponent = physicMapper.get(entity);

	}

	@Override
	public void act(float delta) {

		Body body = physicsBodyComponent.body;
		Vector2 velocity = body.getLinearVelocity();
		Vector2 dampingForce = velocity.scl(-1000.1f);
		body.applyForceToCenter(dampingForce, true);
	}

	@Override
	public void dispose() {

	}
}
