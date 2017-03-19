package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.List;

public class Camera {

	double x;
	double y;
	int width;
	int height;
	Screen screen;
	
	public Camera(Screen screen){
		
		this.screen = screen;
		x = 0;
		y = 400;
		width = screen.width;
		height = screen.height;
		
	}
	
	public void draw(List<Point> l, Graphics2D g){
		
		g.setColor(Color.WHITE);
		for(Point p : l){
			
			if(p.x > this.x && p.x < this.x + this.width){
				g.drawLine((int)(p.x - this.x), (int)p.y, (int)(p.x - this.x), screen.height);
			}
			
		}
	}
	
}
