package com.mygdx.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.mygdx.game.entities.GameObject;
import com.mygdx.game.entities.ObjectInfo;

public class People extends GameObject{

	Body body;
	Path path;
		
	public People(ObjectInfo info, MapProperties properties) {
		super(info, properties);
		
		body = get("body", Body.class);
		body.setUserData(this);
		body.setType(BodyType.DynamicBody);
		body.setLinearDamping(5);
		body.setFixedRotation(true);
		
	}

	public void create() {
		int pathID = get("path", Integer.class);
		PeoplePath pp = (PeoplePath) getState().getTmxRenderer().getInstancedObject(pathID);
		path = pp.createPath();
		path.setTo(get("index", Integer.class, 0));
	}

	public void dispose() {
		getState().deleteBody(body);
	}

	public void render(SpriteBatch sb, ShapeRenderer sr, OrthographicCamera camera) {
		
		
	}

	public boolean update(float delta) {

		Vector2 dir = path.point().cpy().sub(body.getWorldCenter());
		body.applyForceToCenter(dir.cpy().nor().scl(20), true);
		
		if(dir.len() < 0.5f) {
			path.next();
			body.setLinearVelocity(0, 0);
		}
		
		return false;
	}

}
