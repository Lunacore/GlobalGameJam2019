package com.mygdx.game;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.mygdx.game.states.GameOverState;
import com.mygdx.game.states.HouseState;
import com.mygdx.game.states.IntroState;
import com.mygdx.game.states.MarketState;
import com.mygdx.game.states.StreetState;
import com.mygdx.game.states.WinState;

public class MyGdxGame extends ApplicationAdapter {
		
	//HOME IS WHERE YOU CAN SHIT COMFORTABLY
	
	public void create () {
		AwesomeLibGDX.init();
		AwesomeLibGDX.addState(new StreetState());
		AwesomeLibGDX.addState(new HouseState());
		AwesomeLibGDX.addState(new MarketState());
		AwesomeLibGDX.addState(new GameOverState());
		AwesomeLibGDX.addState(new WinState());
		AwesomeLibGDX.addState(new IntroState());

		AwesomeLibGDX.manager.force(5);
		
		Gdx.input.setCursorCatched(true);

		AwesomeLibGDX.create();
	}

	public void render () {
		AwesomeLibGDX.render();
	}
	
	public void dispose () {
		AwesomeLibGDX.dispose();
	}
}
