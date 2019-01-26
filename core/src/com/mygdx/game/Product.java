package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.mygdx.game.entities.ImageGameObject;
import com.mygdx.game.entities.ObjectInfo;
import com.mygdx.game.states.State;

public class Product extends ImageGameObject{

	public boolean isToilet = false;
	public boolean isPhone = false;

	public Product(ObjectInfo info, MapProperties properties) {
		super(info, properties);
		isToilet = get("isToilet", Boolean.class, false);
		isPhone = get("isPhone", Boolean.class, false);

		body.setType(BodyType.DynamicBody);
		body.setLinearDamping(10);
		body.setAngularDamping(10);
		body.setUserData(this);
		
		TextureRegion b = imgObj.getTextureRegion();
//		
//		transform.setPosition(
//				- b.getRegionWidth() * imgObj.getScaleX() /2f,
//				- b.getRegionHeight() * imgObj.getScaleX() /2f);
		
		transform.getPosition().rotate(imgObj.getRotation());


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
	public boolean update(float delta) {
		// TODO Auto-generated method stub
		return false;
	}

}
