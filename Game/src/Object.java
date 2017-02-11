import java.awt.Color;
import java.awt.Graphics;

public class Object {

	int x;
	int y;
	float xV = 0;
	float yV = 1;
	int width = 10;
	int height = 10;
	Draw d;
	
	public Object(int startX, int startY){
		
		x = startX;
		y = startY;
		d = new Draw();
		
	}
	
	public void getPos(){
		x = (int) (x + xV);
		y = (int) (y + yV);
		System.out.println(x);
		System.out.println(y);
	}
	
	public void draw(){
		Graphics g = d.getDraw();
		g.setColor(Color.RED);
		g.fillRect(x, y, width, height);
		g.dispose();
	}
	
}