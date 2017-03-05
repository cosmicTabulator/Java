import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Seeker extends Entity{

	int spawnTime;
	int speed = 100;
	
	BufferedImage img;
	
	public Seeker(Vector pos) {
		super(pos, new Vector(0,0,0));
		this.id = 5;
		this.melee = true;
		this.enemy = true;
		spawnTime = Main.ticks;
		
		try {
		    img = ImageIO.read(getClass().getResource("Textures/Enemy1.png"));
		} catch (IOException e) {
			System.out.println(this);
			System.out.println(e);
		}
	}
	
	@Override
	public void draw(Graphics2D g){
		g.drawImage(img, (int) pos.x, (int) pos.y, width, height, null);
	}
	
	@Override
	public void AI(int ticks){
		if(Main.level > 3){
			speed = 150;
		}
		if(ticks - spawnTime > 20){
		
			Vector playerPos;

			if(getPlayer() != null){
				playerPos = getPlayer().pos;
				Vector dir = Vector.add(Vector.mult(this.pos, -1), playerPos);
				dir = Vector.reduce(dir);
				this.vel = lerp(Vector.mult(dir, speed), this.vel, this.c);
			}
		}
		
		if(isCollided(2, this)){
			kill();
			Main.score = Main.score + 10;
		}
	}
}
