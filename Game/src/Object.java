import java.awt.Color;
import java.awt.Graphics;

public class Object {

	Vector pos;
	Vector vel;
	int width = 10;
	int height = 10;
	
	public Object(Vector pos, Vector vel){
		
		this.pos = pos;
		this.vel = vel;
		this.pos.print();
		this.vel.print();
	}
	
	public void getPos(){
		pos = Vector.add(pos, Vector.mult(vel, Main.timePerTick));
//		System.out.println(pos.x);
//		System.out.println(pos.y);
	}
	
	public void draw(Graphics g){
		g.setColor(Color.RED);
		g.fillRect((int)pos.x, (int)pos.y, width, height);
		return;
	}
	
}
