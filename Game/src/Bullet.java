import java.awt.Color;
import java.awt.Graphics;

public class Bullet extends Object{

	public Bullet(Vector pos, Vector vel) {
		super(pos, vel);
	}

	@Override
	public void onTick(int ticks){
		
		this.ticks = ticks;
		
		if(pos.y > 400 || pos.y < 0){
			kill();
		}
		
	}
	
	@Override
	public void draw(Graphics g){
		g.setColor(Color.YELLOW);
		g.fillRect((int)pos.x, (int)pos.y, 3, 5);
	}
	
}
