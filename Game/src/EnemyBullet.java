import java.awt.Color;
import java.awt.Graphics;

public class EnemyBullet extends Entity{

	public EnemyBullet(Vector pos, Vector vel) {
		super(pos, vel);
		this.id = 4;
		this.melee = true;
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
		g.setColor(Color.GREEN);
		g.fillRect((int)pos.x, (int)pos.y, 3, 5);
	}
	
}
