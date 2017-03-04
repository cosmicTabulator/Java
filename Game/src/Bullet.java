import java.awt.Color;
import java.awt.Graphics;

public class Bullet extends Entity{

	public Bullet(Vector pos, Vector vel) {
		super(pos, vel);
		this.id = 2;
		this.width = 3;
		this.height = 5;
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
		g.fillRect((int)pos.x, (int)pos.y, width, height);
	}
	
}
