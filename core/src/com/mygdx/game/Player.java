package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.animation.AnimationLoader;
import com.mygdx.game.entities.GameParticle;
import com.mygdx.game.entities.ObjectInfo;
import com.mygdx.game.entities.TopDownPlayer;
import com.mygdx.game.helper.Helper;
import com.mygdx.game.input.KeyMapper.Device;
import com.mygdx.game.input.XBoxController;
import com.mygdx.game.states.GameState;
import com.mygdx.game.states.State;

public class Player extends TopDownPlayer{
	
	Texture fart_tex;
	
	float cameraRotation = 0;
	public static float merdometer = 10;
	public static boolean toilet = false;
	public static boolean phone;
	
	float fartValue = 0;
	float fartTween = 0;
	
	boolean finish = false;
	boolean sprint = false;

	BitmapFont font;
	
	float rw = 100;
	float rh = 600;
	
	Texture player_top;
	Animation<TextureRegion> legs;

	
	public Player(ObjectInfo info, MapProperties properties) {
		super(info, properties);
		
		getState().manager.registerKey("Sprint", Device.KEYBOARD, Keys.SPACE);
		getState().manager.registerKey("Sprint", Device.CONTROLLER, XBoxController.BUTTON_A);

		getState().manager.registerKey("Fart", Device.CONTROLLER, XBoxController.BUTTON_B);
		getState().manager.registerKey("Fart", Device.KEYBOARD, Keys.SHIFT_LEFT);

		fart_tex = new Texture("fart.png");
		
		font = Helper.newFont("Allan-Bold.ttf", 24);
		
		player_top = new Texture("char.png");
		legs = AnimationLoader.load("legs.png", 32, 32, 0, 7, 1/15f);
		legs.setPlayMode(PlayMode.LOOP);
		
		getTransform().getScale().set(2, 2);
		
	}

	@Override
	public void render(SpriteBatch sb, ShapeRenderer sr, OrthographicCamera camera) {
		sb.end();
		
		sr.begin(ShapeType.Filled);
		sr.setProjectionMatrix(Helper.getDefaultProjection());
		
		sr.setColor(Color.WHITE);
		sr.rect(49, 49, rw + 2, rh + 2);

		sr.setColor(Color.GREEN.cpy().lerp(Color.RED, merdometer / 100f));
		sr.rect(50, 50, rw, merdometer / 100f * rh);
		
		sr.setColor(Color.GREEN.cpy().lerp(Color.RED, fartValue / 50f));

		sr.circle(50 + rw/2f, 50 + rh + 30, 25);
				
		sr.setColor(Color.WHITE);
		
		Vector2 v = Helper.newPolarVector(fartTween / 50f * 360 + 90, 30);
		
		sr.rectLine(50 + rw/2f, 50 + rh + 30, 50 + rw/2f + v.x, 50 + rh + 30 + v.y, 3);

		
		v = Helper.newPolarVector(90, 25);
		
		sr.rectLine(50 + rw/2f, 50 + rh + 30, 50 + rw/2f + v.x, 50 + rh + 30 + v.y, 1);


		if(Player.toilet)
		sr.rectLine(30, Gdx.graphics.getHeight() - 70, 120, Gdx.graphics.getHeight() - 70, 5);
		if(Player.phone)
		sr.rectLine(30, Gdx.graphics.getHeight() - 100, 70, Gdx.graphics.getHeight() - 100, 5);

		sr.end();
		
		
		sr.setProjectionMatrix(camera.combined);
		
		sb.begin();
		
		sb.setProjectionMatrix(Helper.getDefaultProjection());
		
		sb.setColor(1, 1, 1, 1);
		
		font.draw(sb, "Toilet paper", 30, Gdx.graphics.getHeight() - 60);
		font.draw(sb, "Phone", 30, Gdx.graphics.getHeight() - 90);

		sb.setProjectionMatrix(camera.combined);
		
		
		TextureRegion region = legs.getKeyFrame(Helper.Game.globalTimer * body.getLinearVelocity().len() / 10f);
		
		renderBodyRegion(sb, region, body);
		renderBodyTexture(sb, player_top, body);
		
		getTransform().setAngle(body.getLinearVelocity().angle() - 90);

	}
	
	Vector3 cameraTween = new Vector3();
	
	@Override
	public boolean update(float delta) {
		
		fartTween += (fartValue - fartTween) / 5f;
		
		cameraTween.set(
				(body.getWorldCenter().x - getState().getCamera().position.x) / 5f,
				(body.getWorldCenter().y - getState().getCamera().position.y) / 5f,
				0);
		
		getState().getCamera().position.add(cameraTween);
		
		cameraRotation += delta;
		
		getState().getCamera().up.set(Helper.newPolarVector((float) Math.sin(cameraRotation)*3f + 90, 1), 0);
		
		if(sprint) 	
			merdometer += delta*5;
		else
			merdometer += delta*2;
		
		if(merdometer > 100 & !finish) {
			((GameState)getState()).fade(3);
			finish = true;
		}
		
		if(sprint) {
			setSpeed(20);
		}
		else {
			setSpeed(10);
		}
		
		fartValue -= delta * 5f;
		
		if(fartValue < 0) fartValue = 0;
		
		return super.update(delta);
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
	public void inputIn(Device device, String mapName) {
		
		if(mapName.equals("Sprint")) {
			sprint = true;
		}
		
		if(mapName.equals("Fart")) {
			fartValue += 30;
			merdometer -= 3;
			if(merdometer < 0) merdometer = 0;
		
			if(fartValue > 50) {
				fartValue = 50;
				merdometer += 40;
			}
			
			for(int i = 0; i < 5; i++) {
				GameParticle gp = new GameParticle(info, body.getWorldCenter(), fart_tex);
				gp.setScale(new Vector2(0.7f / State.PHYS_SCALE, 0.7f / State.PHYS_SCALE));
				gp.setVelocity(Helper.randomUnit().scl(0.05f));
				gp.setDrag(0.1f);
				getState().putInScene(gp);
			}
		}
		// TODO Auto-generated method stub
		super.inputIn(device, mapName);
	}
	
	@Override
	public void inputOut(Device device, String mapName) {
		// TODO Auto-generated method stub
		if(mapName.equals("Sprint")) {
			sprint = false;
		}
		super.inputOut(device, mapName);
	}

}
