package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entities.GameObject;
import com.mygdx.game.entities.ObjectInfo;
import com.mygdx.game.states.State;

public class PeoplePath extends GameObject{
	
	ArrayList<Vector2> points;
	boolean loopable;
	boolean backwards = false;
	public int id;

	public PeoplePath(ObjectInfo info, MapProperties properties) {
		super(info, properties);
		
		MapObject mo = get("this", MapObject.class);
		
		id = get("id", Integer.class);
		
		points = new ArrayList<Vector2>();
		
		if(mo instanceof PolylineMapObject) {
			loopable = false;
			backwards = false;
			
			PolylineMapObject pmo = (PolylineMapObject) mo;
			
			for(int i = 0; i < pmo.getPolyline().getTransformedVertices().length/2f; i ++) {
				float x = pmo.getPolyline().getTransformedVertices()[i*2] / State.PHYS_SCALE;
				float y = pmo.getPolyline().getTransformedVertices()[i*2 + 1] / State.PHYS_SCALE;
				
				points.add(new Vector2(x, y));
			}
		}
		
		if(mo instanceof PolygonMapObject) {
			
			loopable = true;
			
			PolygonMapObject pmo = (PolygonMapObject) mo;

			for(int i = 0; i < pmo.getPolygon().getTransformedVertices().length/2f; i ++) {
				float x = pmo.getPolygon().getTransformedVertices()[i*2] / State.PHYS_SCALE;
				float y = pmo.getPolygon().getTransformedVertices()[i*2 + 1] / State.PHYS_SCALE;
				 
				points.add(new Vector2(x, y));
			}
		}
	}
	
	public Path createPath() {
		return new Path(points, loopable);
	}

	public void create() {
		
	}

	public void dispose() {
		
	}

	public void render(SpriteBatch sb, ShapeRenderer sr, OrthographicCamera camera) {
		
	}

	public boolean update(float delta) {
		return false;
	}

}
