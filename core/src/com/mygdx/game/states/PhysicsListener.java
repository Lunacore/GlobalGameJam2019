package com.mygdx.game.states;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.mygdx.game.CameraZoomer;
import com.mygdx.game.Door;
import com.mygdx.game.Player;
import com.mygdx.game.Product;
import com.mygdx.game.Terrain;
import com.mygdx.game.Toilet;
import com.mygdx.game.Door.DoorType;
import com.mygdx.game.entities.GameParticle;
import com.mygdx.game.entities.ObjectInfo;
import com.mygdx.game.helper.Helper;
import com.mygdx.game.states.DefaultStateListener;
import com.mygdx.game.states.State;

public class PhysicsListener extends DefaultStateListener{

	static BitmapFont font;
	
	public PhysicsListener(State state) {
		super(state);
		
		if(font == null) {
			font = Helper.newFont("Allan-Bold.ttf", 24);
		}
	}
	
	@Override
	public void beginContact(Contact contact) {
		
		if(compareCollision(contact, Terrain.class, Player.class)) {
			Terrain t = getInstanceFromContact(contact, Terrain.class);
			if(!t.used) {
				t.used = true;
				Player.merdometer -= 20;
				if(Player.merdometer < 0) Player.merdometer = 0;
				
				Player player = getInstanceFromContact(contact, Player.class);
				GameParticle gp = new GameParticle(new ObjectInfo(state, 10, 1f), player.getBodyPosition(), "Nothing feels like shitting at home...", font);
				gp.setVelocity(Helper.randomUnit().scl(0.3f));
				gp.setDrag(0.1f);
				state.putInScene(gp);

			}
		}
		
		if(compareCollision(contact, Player.class, Toilet.class)) {
			((GameState) state).fade(4);
		}
		
		if(compareCollision(contact, CameraZoomer.class, Player.class)) {
			CameraZoomer zoomer = getInstanceFromContact(contact, CameraZoomer.class);
			((StreetState) state).setZoom(zoomer.zoom / State.PHYS_SCALE);
			return;
		}
		
		if(compareCollision(contact, Door.class, Player.class)) {
			
			Player player = getInstanceFromContact(contact, Player.class);
			Door door = getInstanceFromContact(contact, Door.class);
			
			if(door.type == DoorType.HOUSE) {
				((GameState) state).fade(1);
			}
			if(door.type == DoorType.NOT_HOUSE) {
				GameParticle gp = new GameParticle(new ObjectInfo(state, 10, 1f), player.getBodyPosition(), "This is not my house!", font);
				gp.setVelocity(Helper.randomUnit().scl(0.3f));
				gp.setDrag(0.1f);
					
				state.putInScene(gp);
			}
			
			if(door.type == DoorType.MARKET) {
				((GameState) state).fade(2);
			}
			if(door.type == DoorType.MARKET_EXIT) {
				((GameState) state).fade(0);
			}
		}
		
		if(compareCollision(contact, Product.class, Player.class)) {
			Product prod = getInstanceFromContact(contact, Product.class);
			Player player = getInstanceFromContact(contact, Player.class);

			if(prod.isToilet) {				
				state.removeObject(prod);
				GameParticle gp = new GameParticle(new ObjectInfo(state, 10, 1f), player.getBodyPosition(), "Toilet paper!", font);
				gp.setVelocity(Helper.newPolarVector(90, 0.3f));
				gp.setDrag(0.1f);
				state.putInScene(gp);
				Player.toilet = true;

			}
			if(prod.isPhone) {				
				state.removeObject(prod);
				GameParticle gp = new GameParticle(new ObjectInfo(state, 10, 1f), player.getBodyPosition(), "Phone!", font);
				gp.setVelocity(Helper.newPolarVector(90, 0.3f));
				gp.setDrag(0.1f);
				state.putInScene(gp);
				Player.phone = true;

			}
		}
		
		super.beginContact(contact);
	}

}
