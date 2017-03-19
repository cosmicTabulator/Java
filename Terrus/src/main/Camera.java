package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.List;

public class Camera {

	int width;
	int height;
	Vector pos;
	Screen screen;
	
	public Camera(Screen screen){
		
		this.screen = screen;
		pos = new Vector(0,200);
		width = screen.width;
		height = screen.height;
		
	}
	
	public void draw(Tile t, Graphics2D g){
		
		Vector offset = Vector.mult(pos, -10);
		t.draw(g, offset);
		
	}
	
//	public void draw(List<Point> l, Graphics2D g){
//		
//		g.setColor(Color.WHITE);
//		for(Point p : l){
//			
//			if(p.x > this.x && p.x < this.x + this.width){
//				g.drawLine((int)(p.x - this.x), (int)p.y, (int)(p.x - this.x), screen.height);
//			}
//			
//		}
//	}
	
}
