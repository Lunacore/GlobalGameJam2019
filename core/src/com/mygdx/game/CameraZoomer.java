package com.mygdx.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.entities.GameObject;
import com.mygdx.game.entities.ObjectInfo;

public class CameraZoomer extends GameObject{

	Body body;
	public float zoom;
	
	public CameraZoomer(ObjectInfo info, MapProperties properties) {
		super(info, properties);
		
		body = get("body", Body.class);
		body.setUserData(this);
		
		zoom = get("zoom", Float.class, 1f);
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
