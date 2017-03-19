package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//import main.World.Zone;

public class Main {
	
	long lastTick = 0;
	public static boolean running = true;
	
	Camera c;
	Generator gen;
	Tile test;
	World world;
	static Screen screen;
	Set<Integer> loaded = new HashSet<Integer>();
	
	public Main(Screen screen){
		
		gen = new Generator(150, 10, new Vector(0, 200), new Vector(400, 200));
		
		List<Vector> points = gen.generate();
		
		world = new World();
		
		points = Vector.interpolate(points);
		
		for(Vector v : points){
			Tile t = new Tile(v);
			world.addTile(t); 
			for(int i = (int) v.y; i < 400; i++){
				world.addTile(new Tile(new Vector(v.x, i)));
			}
		}
		
//		loaded.add(0);
		
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
				
//				for(Zone z : world.getZones(c)){
//					for(Tile t : z.zone){
//						if(t.inViewRange(c)){
//							c.draw(t, g);
//						}
//					}
//				}
				
				for(Tile t : world.tiles){
					c.draw(t, g);
				}
				
				g.dispose();
				screen.pane.repaint();
				
				if(Screen.keys.contains(KeyEvent.VK_S)){
					c.pos.y += 1;
				}
				if(Screen.keys.contains(KeyEvent.VK_W)){
					c.pos.y -= 1;
				}
				if(Screen.keys.contains(KeyEvent.VK_D)){
					c.pos.x += 1;
				}
				if(Screen.keys.contains(KeyEvent.VK_A)){
					c.pos.x -= 1;
				}
				if(Screen.keys.contains(KeyEvent.VK_Q)){
					System.exit(0);
				}
			
//				if(!loaded.contains(Math.floorDiv((int) c.x,800))){
//					Generator q = new Generator(300, 10, new Point(Math.floorDiv((int) c.x,800)*800, 400), new Point(Math.floorDiv((int) c.x,800)*800+800, 400));
//					List<Point> points = q.generate();
//					lists.add(Point.interpolate(points));
//					loaded.add(Math.floorDiv((int) c.x,800));
//				}
//				if(!loaded.contains(Math.floorDiv((int) c.x,800) + 1)){
//					Generator q = new Generator(300, 10, new Point(Math.floorDiv((int) c.x,800)*800+800, 400), new Point(Math.floorDiv((int) c.x,800)*800+1600, 400));
//					List<Point> points = q.generate();
//					lists.add(Point.interpolate(points));
//					loaded.add(Math.floorDiv((int) c.x,800) + 1);
//				}
			}
		}
	}
	
	public static void main(String[] args) {
		
		screen = new Screen(800, 800);
		new Main(screen);

	}

}