import java.awt.Color;
import java.awt.Graphics;

public class EnemyBullet extends Entity{

	public EnemyBullet(Vector pos, Vector vel) {
		super(pos, vel);
		this.id = 4;
		this.melee = true;
		this.width = 3;
		this.height = 5;
	}

	@Override
	public void onTick(int ticks){
		
		this.ticks = ticks;
		
		if(pos.y > 300 || pos.y < 0 || pos.x > 400 || pos.x < 0){
			kill();
		}
		
	}
	
	@Override
	public void draw(Graphics g){
		g.setColor(Color.MAGENTA);
		g.fillRect((int)pos.x, (int)pos.y, width, height);
	}
	
}
