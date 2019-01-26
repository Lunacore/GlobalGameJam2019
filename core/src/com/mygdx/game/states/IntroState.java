package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Player;
import com.mygdx.game.helper.Helper;

public class IntroState extends State{

	BitmapFont font;
	GlyphLayout layout;
	float alpha1 = 0;
	float alpha2 = 0;
	
	String texts[] = new String[] {
			"Nothing feels more like home...",
			"Than the place you take a shit most comfortably..."
	};
	
	public void create() {
		super.create();
		
		font = Helper.newFont("Allan-Bold.ttf", 38);
		layout = new GlyphLayout();
		
		
		
		Player.merdometer = 10;
		Player.phone = false;
		Player.toilet = false;
	}
	
	public void enter() {
		alpha1 = 0;
		alpha2 = 0;
	}

	public void render(SpriteBatch sb) {
		sb.setProjectionMatrix(Helper.getDefaultProjection());
		
		sb.begin();
		layout.setText(font, texts[0]);
		font.setColor(1, 1, 1, alpha1/2f - 0.25f);
		font.draw(sb, texts[0], (Gdx.graphics.getWidth() - layout.width)/2f + alpha2*10, (Gdx.graphics.getHeight() + layout.height)/2f);
		
		layout.setText(font, texts[1]);
		font.setColor(1, 1, 1, alpha2/2f - 2f);
		font.draw(sb, texts[1], (Gdx.graphics.getWidth() - layout.width)/2f + (alpha2-3)*10, (Gdx.graphics.getHeight() + layout.height)/2f);
		
		
		sb.end();
	}

	public void update(float delta) {
		
		if(alpha2 > 3)
			alpha1 -= delta;
		else
			alpha1 += delta;
		
		
		alpha2 += delta;
		
		if(alpha2 > 9) {
			manager.getState(0).dispose();
			manager.getState(1).dispose();
			manager.getState(2).dispose();
			manager.changeState(0);
			
		}

		
		System.out.println(alpha2);
	}
	
	@Override
	public boolean keyDown(int keycode) {
		alpha2 = 9;
		return super.keyDown(keycode);
	}
	
	@Override
	public boolean buttonDown(Controller controller, int buttonCode) {
		alpha2 = 9;
		return super.buttonDown(controller, buttonCode);
	}

}
