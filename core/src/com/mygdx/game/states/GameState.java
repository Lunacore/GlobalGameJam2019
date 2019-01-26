package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.mygdx.game.helper.Helper;
import com.mygdx.game.states.State;

public class GameState extends State{

	boolean fadeOut = false;
	boolean fadeIn;
	float alpha = 0;
	
	@Override
	public void create() {
		super.create();
		
	}
	
	public void enter() {
		alpha = 1f;
		fadeIn = true;
		fadeOut = false;
		
		setInputEnabled(true);
	}
	
	int nextState = 1;
	
	public void fade(int state) {
		setInputEnabled(false);
		nextState = state;
		fadeOut = true;
		fadeIn = false;
		alpha = 0;
	}

	@Override
	public void render(SpriteBatch sb) {
		// TODO Auto-generated method stub
		
	}
	
	
	public void postRender(SpriteBatch sb) {
		super.postRender(sb);
		
		Helper.enableBlend();
		
		sr.begin(ShapeType.Filled);
		sr.setProjectionMatrix(Helper.getDefaultProjection());
		sr.setColor(0, 0, 0, alpha);
		sr.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		sr.end();
		
		Helper.disableBlend();
	}

	public void update(float delta) {
				
		if(fadeOut) {
			alpha += delta * 2f;
		}
		
		if(fadeIn) {
			alpha -= delta * 2f;
		}
		
		if(alpha > 1) {
			alpha = 1;
			manager.flipState(nextState);
			fadeOut = false;
		}
		
		if(alpha < 0) {
			fadeIn = false;
			alpha = 0;
		}
		
		
	}

}
