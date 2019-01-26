package com.mygdx.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.entities.GameObject;
import com.mygdx.game.entities.ObjectInfo;

public class Terrain extends GameObject{

	Body body;
	public boolean used = false;
	
	public Terrain(ObjectInfo info, MapProperties properties) {
		super(info, properties);
		
		body = get("body", Body.class);
		body.setUserData(this);
		body.getFixtureList().get(0).setSensor(true);
	}

	public void create() {
		
	}

	public void dispose() {
		getState().deleteBody(body);
	}

	public void render(SpriteBatch sb, ShapeRenderer sr, OrthographicCamera camera) {
		
	}

	public boolean update(float delta) {
		return false;
	}

}
