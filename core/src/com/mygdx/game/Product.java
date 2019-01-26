package com.mygdx.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.mygdx.game.entities.GameObject;
import com.mygdx.game.entities.ObjectInfo;

public class Product extends GameObject{

	Body body;
	public boolean isToilet = false;
	public boolean isPhone = false;

	public Product(ObjectInfo info, MapProperties properties) {
		super(info, properties);
		isToilet = get("isToilet", Boolean.class, false);
		isPhone = get("isPhone", Boolean.class, false);

		body = get("body", Body.class);
		body.setType(BodyType.DynamicBody);
		body.setLinearDamping(10);
		body.setAngularDamping(10);
		body.setUserData(this);

	}

	@Override
	public void create() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		getState().deleteBody(body);
	}

	@Override
	public void render(SpriteBatch sb, ShapeRenderer sr, OrthographicCamera camera) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean update(float delta) {
		// TODO Auto-generated method stub
		return false;
	}

}
