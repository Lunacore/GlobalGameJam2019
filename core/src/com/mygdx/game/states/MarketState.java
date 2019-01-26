package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Player;
import com.mygdx.game.helper.Helper;
import com.mygdx.game.input.KeyMapper.Device;
import com.mygdx.game.states.State;

public class MarketState extends GameState{


	public void create() {
		super.create();
		enablePhysics(new PhysicsListener(this));
		enableDebugDraw();
		setGravity(Vector2.Zero);
		
		setTmxMap("tiled/maps/market.tmx", 1f);
		
		Player player = (Player) getByClass(Player.class).get(0);
		getCamera().position.set(player.getBodyPosition().x, player.getBodyPosition().y, 0);
		
		
		if(Gdx.input.isKeyPressed(Keys.W)) {
			player.inputIn(Device.KEYBOARD, "Up");
		}
		
	}

	@Override
	public void render(SpriteBatch sb) {
		// TODO Auto-generated method stub
		
	}

}
