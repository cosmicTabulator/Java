package main;

import java.awt.Color;
import java.awt.Graphics2D;

public class Tile {

	int width;
	int height;
	Vector pos;
	Vector screenPos;
	Color color;
	
	public Tile(Vector pos){
		this.pos = pos;
		this.screenPos = Vector.mult(pos, 10);
		width = 10;
		height = 10;
		color = Color.white;
	}
	
	public void draw(Graphics2D g, Vector offset){
		this.screenPos = Vector.mult(pos, 10);
		g.setColor(color);
		g.fillRect((int)(screenPos.x + offset.x), (int)(screenPos.y + offset.y), width, height);
	}

	public boolean inViewRange(Camera c) {
		
		boolean out = false;
		
		if(this.pos.x >= c.pos.x && this.pos.x <= c.pos.x + c.width){
			if(this.pos.y >= c.pos.y && this.pos.y <= c.pos.y + c.height){
				out = true;
			}
		}
		
		return out;
	}
	
}
