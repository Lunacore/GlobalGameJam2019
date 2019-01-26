package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.helper.Helper;

public class GameOverState extends State{

	BitmapFont font;
	GlyphLayout layout;
	float alpha = 0;
	
	public void create() {
		super.create();
		
		font = Helper.newFont("Allan-Bold.ttf", 38);
		layout = new GlyphLayout();
	}
	
	public void enter() {
		alpha = 0;
	}

	public void render(SpriteBatch sb) {
		sb.setProjectionMatrix(Helper.getDefaultProjection());
		
		sb.begin();
		layout.setText(font, "You shat yourself...");
		
		font.setColor(1, 1, 1, alpha/2f - 0.25f);
		
		font.draw(sb, "You shat yourself...", (Gdx.graphics.getWidth() - layout.width)/2f + alpha*10, (Gdx.graphics.getHeight() + layout.height)/2f);
		sb.end();
	}

	public void update(float delta) {
		alpha += delta;
	}
	
	@Override
	public boolean keyDown(int keycode) {
		manager.changeState(5);
		return super.keyDown(keycode);
	}
	
	@Override
	public boolean buttonDown(Controller controller, int buttonCode) {
		manager.changeState(5);
		return super.buttonDown(controller, buttonCode);
	}

}
