import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Set;

public class Object {

	Vector pos;
	Vector vel;
	int width = 10;
	int height = 10;
	int ticks = 0;
	float c = 0.05f;
	int v = 75;
	
	public Object(Vector pos, Vector vel){
		
		this.pos = pos;
		this.vel = vel;
		this.pos.print();
		this.vel.print();
	}
	
	public void getPos(){
		
		checkActionMap(Screen.keys);
		
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
		
		//Leave Empty for other Class utilizations
		
	}
	
	float lerp(float value, float target, float weight){
		
		float out = (value + target*weight)/(1 + weight);
		
		return out;
	}
	
	void kill(){
		Main.newObjects.remove(this);
	}
	
}
