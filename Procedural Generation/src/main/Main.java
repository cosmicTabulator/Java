package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
	
	long lastTick = 0;
	public static boolean running = true;
	
	Camera c;
	Generator gen;
	static Screen screen;
	Set<Integer> loaded = new HashSet<Integer>();
	List<List<Point>> lists = new ArrayList<List<Point>>();
	
	public Main(Screen screen){
		
		gen = new Generator(300, 10, new Point(0, 400), new Point(800, 400));
		
		List<Point> points = gen.generate();
		
		lists.add(Point.interpolate(points));
		
		loaded.add(0);
		
//		Graphics2D g = screen.image.createGraphics();
//		
//		g.setColor(Color.white);
//		
//		for(Point p : lPoints){
//			g.drawLine((int)p.x, (int)p.y, (int)p.x, 800);
//		}
//		
//		g.dispose();
//		
//		screen.pane.repaint();
		
		c = new Camera(screen);
		
		loop();
	}
	
	private void loop(){
		
		while(running){
			
			if(System.currentTimeMillis() - lastTick >= 20){
				lastTick = System.currentTimeMillis();
				
				Graphics2D g = screen.image.createGraphics();
				g.setColor(Color.BLACK);
				g.fillRect(0, 0, 800, 800);
				
				for(List<Point> l : lists){
					c.draw(l, g);
				}
				
				g.dispose();
				screen.pane.repaint();
				
				if(Screen.keys.contains(KeyEvent.VK_D)){
					c.x += 10;
				}
				if(Screen.keys.contains(KeyEvent.VK_A)){
					c.x -= 10;
				}
				if(Screen.keys.contains(KeyEvent.VK_Q)){
					System.exit(0);
				}
			
				if(!loaded.contains(Math.floorDiv((int) c.x,800))){
					Generator q = new Generator(300, 10, new Point(Math.floorDiv((int) c.x,800)*800, 400), new Point(Math.floorDiv((int) c.x,800)*800+800, 400));
					List<Point> points = q.generate();
					lists.add(Point.interpolate(points));
					loaded.add(Math.floorDiv((int) c.x,800));
				}
				if(!loaded.contains(Math.floorDiv((int) c.x,800) + 1)){
					Generator q = new Generator(300, 10, new Point(Math.floorDiv((int) c.x,800)*800+800, 400), new Point(Math.floorDiv((int) c.x,800)*800+1600, 400));
					List<Point> points = q.generate();
					lists.add(Point.interpolate(points));
					loaded.add(Math.floorDiv((int) c.x,800) + 1);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		
		screen = new Screen(800, 800);
		new Main(screen);

	}

}
