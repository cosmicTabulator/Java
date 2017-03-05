import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Shooter extends Entity{

	int timer;
	boolean moving = false;
	Random rand = new Random();
	
	BufferedImage img;
	
	public Shooter(Vector pos) {
		super(pos, new Vector(0,0,0));
		this.id = 6;
		this.enemy = true;
		this.melee = true;
		
		try {
		    img = ImageIO.read(getClass().getResource("Textures/Enemy2.png"));
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
		
		if(timer > 50 && !moving){
			moving = true;
			this.vel.x = rand.nextFloat();
			this.vel.y = rand.nextFloat();

			if((rand.nextBoolean() && this.pos.x > 30) || this.pos.x > 370){
				this.vel.x = this.vel.x*-1;
			}
			if((rand.nextBoolean() && this.pos.y > 30) || this.pos.y > 120){
				this.vel.y = this.vel.y*-1;
			}
			
			this.vel = Vector.mult(Vector.reduce(this.vel), 70);
			
			timer = 0;
		}
		if(timer > 25 && moving){
			moving = false;
			this.vel.x = 0;
			this.vel.y = 0;
			if(Main.level > 7){
			
				Main.addObject(new EnemyBullet(pos, new Vector(0, 200, 0)));
				Main.addObject(new EnemyBullet(pos, new Vector(200, 0, 0)));
				Main.addObject(new EnemyBullet(pos, new Vector(0, -200, 0)));
				Main.addObject(new EnemyBullet(pos, new Vector(-200, 0, 0)));
				Main.addObject(new EnemyBullet(pos, new Vector(200, 200, 0)));
				Main.addObject(new EnemyBullet(pos, new Vector(-200, 200, 0)));
				Main.addObject(new EnemyBullet(pos, new Vector(200, -200, 0)));
				Main.addObject(new EnemyBullet(pos, new Vector(-200, -200, 0)));
				
			}
			else{
				Main.addObject(new EnemyBullet(pos, new Vector(0, 200, 0)));
				Main.addObject(new EnemyBullet(pos, new Vector(200, 0, 0)));
				Main.addObject(new EnemyBullet(pos, new Vector(0, -200, 0)));
				Main.addObject(new EnemyBullet(pos, new Vector(-200, 0, 0)));
			}
		}
		
		timer++;
		
		if(isCollided(2, this)){
			kill();
			Main.score = Main.score + 20;
		}
		
	}

	
	
}
