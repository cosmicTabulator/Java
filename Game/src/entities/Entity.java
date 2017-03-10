package entities;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import gui.Arcade;
import main.Main;
import main.Screen;
import main.Vector;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@SuppressWarnings("unused")
public class Entity {

	Vector pos;
	Vector vel;
	int width = 10;
	int height = 10;
	int ticks = 0;
	float c = 0.05f;
	int v = 75;
	public int id = 0;
	public int intId = -1;
	double rot = 0;
	boolean melee = false;
	public boolean enemy = false;
	boolean useRect = true;
	
	Rectangle2D shape;
	
	BufferedImage img;
	
	public Entity(Vector pos, Vector vel){
		
		this.pos = pos;
		this.vel = vel;
		
	}
	
	public void getPos(){
		pos = Vector.add(pos, Vector.mult(vel, Main.fps));
	}
	
	public void draw(Graphics2D g){
		AffineTransform identity = new AffineTransform();
		AffineTransform trans = new AffineTransform();
		trans.setTransform(identity);
		trans.setToTranslation(pos.x, pos.y);
		trans.rotate(Math.toRadians(rot));
		g.drawImage(img, trans, null);
	}
	
	public void checkActionMap(Set<Integer> s){
		
		//Leave Empty for other Class utilizations
		
	}
	
	public void onTick(int ticks){
		
		this.ticks = ticks;
		checkActionMap(Screen.keys);
		AI(ticks);
		//Leave Empty for other Class utilizations
		
	}
	
	public void AI(int ticks){
		
		//Write your AIs here
		
	}
	
	float lerp(float value, float target, float weight){
		
		float out = (value + target*weight)/(1 + weight);
		
		return out;
	}
	
	Vector lerp(Vector value, Vector target, float c) {

		Vector out;
		
		out = new Vector(lerp(value.x, target.x, c),lerp(value.y, target.y, c),lerp(value.z, target.z, c));
		
		return out;
	}
	
	public void kill(){
		Arcade.newObjects.remove(this);
		if(this.enemy){
			Arcade.killCount++;
		}
	}
	
	boolean inBounds(Entity o, Entity e){
		
		boolean out = false;
		
//		if(o.pos.x < x2 && o.pos.x + o.width > x1 && o.pos.y < y2 && o.pos.y + o.height > y1){
//			out = true;
//		}
		AffineTransform identity = new AffineTransform();
		
		Shape rect1 = new Rectangle2D.Float(o.pos.x, o.pos.y, o.width, o.height);
		

		AffineTransform trans1 = new AffineTransform();
		
		trans1.setTransform(identity);
		trans1.rotate(Math.toRadians(o.rot));
		
		rect1 = trans1.createTransformedShape(rect1);
		
		Shape rect2 = new Rectangle2D.Float(e.pos.x, e.pos.y, e.width, e.height);
		

		AffineTransform trans2 = new AffineTransform();
		
		trans2.setTransform(identity);
		trans2.rotate(Math.toRadians(o.rot));
		
		rect2 = trans2.createTransformedShape(rect2);
		
		Area a1 = new Area(rect1);
		Area a2 = new Area(rect2);
		
		a1.intersect(a2);
		
		out = !a1.isEmpty();
		
		return out;
		
	}
	
	List<Entity> collided(Entity c){
		List<Entity> s = new ArrayList<Entity>();
		
		for (Entity o : Arcade.objects){
			if(inBounds(o, c)){
				s.add(o);
			}
		}
		
		return s;
	}
	
	boolean isCollided(int id, Entity c){
		boolean out = false;
		for(Entity o : Arcade.objects){
			if(inBounds(o, c)){
				if(o.id == id){
					out = true;
				}
			}
		}
		
		return out;
		
	}
	
	Entity closest(Entity o, int[] idArray, boolean ignore){
		
		float x;
		float y;
		double h;
		double closestDist = Double.MAX_VALUE;
		Entity closest = null;
		
		for(Entity i: Arcade.objects){
			
			x = i.pos.x - o.pos.x;
			y = i.pos.y - o.pos.y;
			h = Math.sqrt(x*x + y*y);
			
			if(h < closestDist){
				boolean inArray = false;
				for(int q : idArray){
					if(i.id == q){
						inArray = true;
					}
				}
				if(inArray != ignore){
					closestDist = h;
					closest = i;
				}
			}
		}
		
		return closest;
		
	}
	
	Entity getPlayer(){
		
		Entity player = null;
		
		for(Entity o : Arcade.objects){
			if(o.id == 1){
				player = o;
			}
		}
		
		
		return player;
	}
	
	double dist(Vector pos1, Vector pos2){
		
		double distance = 0;
		
		distance = Math.sqrt(Math.pow((pos1.x - pos2.x), 2) + Math.pow((pos1.y - pos2.y), 2));
		
		return distance;
	}
	
}