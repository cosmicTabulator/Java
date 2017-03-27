package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
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
		
		if(t != null){
			Vector offset = Vector.mult(pos, -10);
			t.draw(g, offset);
		}
		
	}
	
	public List<Integer> getCoords(){
		
		List<Integer> coords = new ArrayList<Integer>();
		Vector offset = Vector.mult(pos, -1);
		
		for(int cX = 0; cX < this.width/10; cX++){
			for(int cY = 0; cY < this.height/10; cY++){
				int x = (int) (cX + pos.x);
				int y = (int) (cY + pos.y);
				Pair key = new Pair(x,y);
				coords.add(key.hashCode());
			}
		}
		
//		if(this.pos.x >= c.pos.x && this.pos.x <= c.pos.x + c.width){
//			if(this.pos.y >= c.pos.y && this.pos.y <= c.pos.y + c.height){
//				out = true;
//			}
//		}
		
//		System.exit(0);
		
		return coords;
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
