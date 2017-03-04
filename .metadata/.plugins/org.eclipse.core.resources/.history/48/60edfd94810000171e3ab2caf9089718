import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Shooter extends Entity{

	int timer;
	boolean moving = false;
	Random rand = new Random();
	
	public Shooter(Vector pos) {
		super(pos, new Vector(0,0,0));
		this.id = 6;
		this.enemy = true;
		this.melee = true;
	}
	
	@Override
	public void draw(Graphics g){
		g.setColor(Color.CYAN);
		g.fillOval((int)this.pos.x, (int)this.pos.y, this.width, this.height);
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
			
			this.vel = Vector.mult(Vector.reduce(this.vel), 50);
			
			timer = 0;
		}
		if(timer > 25 && moving){
			moving = false;
			this.vel.x = 0;
			this.vel.y = 0;
			Main.addObject(new EnemyBullet(pos, new Vector(0, 200, 0)));
			Main.addObject(new EnemyBullet(pos, new Vector(200, 0, 0)));
			Main.addObject(new EnemyBullet(pos, new Vector(0, -200, 0)));
			Main.addObject(new EnemyBullet(pos, new Vector(-200, 0, 0)));
		}
		
		timer++;
		
		if(isCollided(2, this)){
			kill();
			Main.score = Main.score + 20;
		}
		
	}

	
	
}
