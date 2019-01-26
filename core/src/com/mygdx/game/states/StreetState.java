package com.mygdx.game.states;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Door;
import com.mygdx.game.Player;
import com.mygdx.game.Door.DoorType;
import com.mygdx.game.entities.GameObject;
import com.mygdx.game.helper.Helper;
import com.mygdx.game.states.State;

public class StreetState extends GameState{

	float zoom;
	

	public void create() {
		super.create();
		enablePhysics(new PhysicsListener(this));
		setGravity(new Vector2(0, 0));
		enableDebugDraw();
		setTmxMap("tiled/maps/street.tmx", 1f);
		
		zoom = getCamera().zoom;
		manager.setTransitionSpeed(1f);
		setInputEnabled(true);
		
		ArrayList<GameObject> doors = getByClass(Door.class);
		
		Player player = (Player) getByClass(Player.class).get(0);
		getCamera().position.set(player.getBodyPosition().x, player.getBodyPosition().y, 0);

		Door d = ((Door)doors.get((int) Helper.random(0, doors.size())));
		
		while(d.type != DoorType.NOT_HOUSE) {
			d = ((Door)doors.get((int) Helper.random(0, doors.size())));
		}
		d.type = DoorType.HOUSE;

	}
	

	
	public void setZoom(float zoom) {
		this.zoom = zoom;
	}

	public void render(SpriteBatch sb) {
		
	}
	
	@Override
	public void update(float delta) {
		super.update(delta);
		
		getCamera().zoom += (zoom - getCamera().zoom)/20f;

	}


}
