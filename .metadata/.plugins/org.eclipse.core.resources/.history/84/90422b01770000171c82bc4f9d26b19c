import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Entity {

	Vector pos;
	Vector vel;
	int width = 10;
	int height = 10;
	int ticks = 0;
	float c = 0.05f;
	int v = 75;
	int id = 0;
	int intId = -1;
	double rot = 0;
	boolean melee = false;
	boolean enemy = false;
	
	public Entity(Vector pos, Vector vel){
		
		this.pos = pos;
		this.vel = vel;
	}
	
	public void getPos(){
		pos = Vector.add(pos, Vector.mult(vel, Main.fps));
	}
	
	public void draw(Graphics g){
		g.setColor(Color.RED);
		g.fillRect((int)pos.x, (int)pos.y, width, height);
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
	
	void kill(){
		Main.newObjects.remove(this);
		if(this.enemy){
			Main.killCount++;
		}
	}
	
	boolean inBounds(Entity o, float x, float y, float f, float g){
		
		boolean out = false;
		List<Vector> points = new ArrayList<Vector>();
		points.add(new Vector(o.pos.x, o.pos.y, 0));
		points.add(new Vector(o.pos.x + o. width, o.pos.y, 0));
		points.add(new Vector(o.pos.x, o.pos.y + o.height, 0));
		points.add(new Vector(o.pos.x + o.width, o.pos.y + o.height, 0));
		for(Vector v : points){
			if(v.x > x && v.x < f && v.y > y && v.y < g){
				out = true;
			}
		}
		
		return out;
		
	}
	
	List<Entity> collided(Entity c){
		List<Entity> s = new ArrayList<Entity>();
		
		for (Entity o : Main.objects){
			if(inBounds(o, c.pos.x, c.pos.y, c.pos.x + c.height, c.pos.y + c.width)){
				s.add(o);
			}
		}
		
		return s;
	}
	
	boolean isCollided(int id, Entity c){
		boolean out = false;
		for(Entity o : Main.objects){
			if(inBounds(o, c.pos.x, c.pos.y, c.pos.x + c.height, c.pos.y + c.width)){
				if(o.id == id){
					out = true;
				}
			}
		}
		
		return out;
		
	}
	
	Entity closest(Entity o, int[] idArray, boolean ignore){
		
		int closestId;
		float x;
		float y;
		double h;
		double closestDist = Double.MAX_VALUE;
		Entity closest = null;
		
		for(Entity i: Main.objects){
			
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
					closestId = i.intId;
					closest = i;
				}
			}
		}
		
		return closest;
		
	}
	
	Entity getPlayer(){
		
		Entity player = null;
		
		for(Entity o : Main.objects){
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
