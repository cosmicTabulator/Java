import java.awt.Color;
import java.awt.Graphics;

public class Object {

	Vector pos;
	Vector vel;
	int width = 10;
	int height = 10;
	Draw d;
	
	public Object(int startX, int startY){
		
		pos = new Vector(startX, startY);
		vel = new Vector(0,0);
		d = new Draw();
	}
	
	public Object(Vector pos, Vector vel){
		
		this.pos = pos;
		this.vel = vel;
		d = new Draw();
	}
	
	public void getPos(){
		pos = Vector.add(pos, vel);
		System.out.println(pos.x);
		System.out.println(pos.y);
	}
	
	public void draw(){
		Graphics g = d.getDraw();
		g.setColor(Color.RED);
		g.fillRect((int)pos.x, (int)pos.y, width, height);
		g.dispose();
	}
	
}
