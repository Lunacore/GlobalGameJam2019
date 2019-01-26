package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Player;
import com.mygdx.game.helper.Helper;

public class WinState extends State{

	BitmapFont font;
	GlyphLayout layout;
	float alpha = 0;
	float alpha2 = 0;

	String speeches[] = new String[] {
			"You did it! You can shit peacefully in your home...",
			"You did manage to get in your home... but there is no Toilet Paper!",
			".. sadly you didn't bring your phone... this looks boring....",
			"Well, at least you bring your phone..."
	};
	
	public void create() {
		super.create();
		
		font = Helper.newFont("Allan-Bold.ttf", 38);
		layout = new GlyphLayout();
	}
	
	public void enter() {
		
	}

	public void render(SpriteBatch sb) {
		sb.setProjectionMatrix(Helper.getDefaultProjection());
		
		sb.begin();
		String txt = speeches[0];
		if(!Player.toilet)
			txt = speeches[1];
		
		layout.setText(font, txt);
		font.setColor(1, 1, 1, alpha/2f - 0.25f);
		font.draw(sb, txt, (Gdx.graphics.getWidth() - layout.width)/2f + alpha2*10, (Gdx.graphics.getHeight() + layout.height)/2f);
		
		if(!Player.phone) {
			layout.setText(font, speeches[2]);
			font.setColor(1, 1, 1, alpha2/2f - 2f);
			font.draw(sb, speeches[2], (Gdx.graphics.getWidth() - layout.width)/2f + (alpha2-3)*10, (Gdx.graphics.getHeight() + layout.height)/2f);
		}
		else if(!Player.toilet) {
			layout.setText(font, speeches[3]);
			font.setColor(1, 1, 1, alpha2/2f - 2f);
			font.draw(sb, speeches[3], (Gdx.graphics.getWidth() - layout.width)/2f + (alpha2-3)*10, (Gdx.graphics.getHeight() + layout.height)/2f);
		}
		
		sb.end();
	}

	public void update(float delta) {
		
		if(alpha2 > 3)
			alpha -= delta;
		else
			alpha += delta;
		
		
		alpha2 += delta;
	}

	
	@Override
	public boolean keyDown(int keycode) {
		if(alpha2 > 9)
		manager.changeState(5);
		return super.keyDown(keycode);
	}
	
	@Override
	public boolean buttonDown(Controller controller, int buttonCode) {
		if(alpha2 > 9)
		manager.changeState(5);
		return super.buttonDown(controller, buttonCode);
	}


}
