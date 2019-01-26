package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;

public class Path {
	
	ArrayList<Vector2> points;
	boolean loopable;
	boolean backwards = false;
	int current = 0;
	
	public Path(ArrayList<Vector2> points, boolean loopable) {
		super();
		this.points = points;
		this.loopable = loopable;
		this.backwards = false;
	}
	
	public void setTo(int index) {
		current = index;
	}
	
	public void next() {
		if(!backwards) {
			current ++;
			
			if(current >= points.size()) {
				if(loopable) {
					current = 0;
				}
				else {
					backwards = true;
					current --;
					current --;
				}
			}
		}
		else {
			current --;
			if(current < 0) {
				current = 1;
				backwards = false;
			}
			
		}
	}
	
	public Vector2 point() {
		return points.get(current);
	}
	

}
