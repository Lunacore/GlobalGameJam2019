package com.mygdx.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.mygdx.game.animation.AnimationLoader;
import com.mygdx.game.entities.GameObject;
import com.mygdx.game.entities.ObjectInfo;
import com.mygdx.game.helper.Helper;

public class People extends GameObject{

	Body body;
	Path path;
		
	static Texture[] texs;
	static Animation<TextureRegion> legs;

	Texture pip;
	
	public People(ObjectInfo info, MapProperties properties) {
		super(info, properties);
		
		body = get("body", Body.class);
		body.setUserData(this);
		body.setType(BodyType.DynamicBody);
		body.setLinearDamping(5);
		body.setFixedRotation(true);
		
		if(texs == null) {
			texs = new Texture[7];
			for(int i = 0; i < 7; i ++) {
				texs[i] = new Texture("peoples/people" + i + ".png");
			}
		}
		
		pip = texs[(int) (Math.random() * texs.length)];
		if(legs == null) {
			legs = AnimationLoader.load("legs.png", 32, 32, 0, 7, 1/15f);
			legs.setPlayMode(PlayMode.LOOP);
		}
		getTransform().getScale().set(2, 2);

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
		
		TextureRegion region = legs.getKeyFrame(Helper.Game.globalTimer * body.getLinearVelocity().len() / 10f);
		
		renderBodyRegion(sb, region, body);
		renderBodyTexture(sb, pip, body);
		
		getTransform().setAngle(body.getLinearVelocity().angle() - 90);
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
