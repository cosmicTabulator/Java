import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;

public class EnemyBullet extends Entity{

	
	public EnemyBullet(Vector pos, Vector vel) {
		super(pos, vel);
		this.id = 4;
		this.melee = true;
		this.width = 3;
		this.height = 5;
		
		try {
		    img = ImageIO.read(getClass().getResource("Textures/Bullet2.png"));
		} catch (IOException e) {
			System.out.println(this);
			System.out.println(e);
		}
	}

	@Override
	public void onTick(int ticks){
		
		this.ticks = ticks;
		
		if(pos.y > 300 || pos.y < 0 || pos.x > 400 || pos.x < 0){
			kill();
		}
		
	}
	
	@Override
	public void draw(Graphics2D g){
		g.drawImage(img, (int) pos.x, (int) pos.y, width, height, null);
	}
	
}
