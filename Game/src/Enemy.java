import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;;

public class Enemy extends Entity{

	int lastShot = 0;
	int nextShot = 0;
	Random rand = new Random();
	
	public Enemy(Vector pos) {
		super(pos, new Vector(0,0,0));

		this.enemy = true;
		this.id = 3;
		
	}
	
	@Override
	public void draw(Graphics g){
		g.setColor(Color.GREEN);
		g.fillRect((int)pos.x, (int)pos.y, width, height);
	}
	
	@Override
	public void onTick(int ticks){
		if(isCollided(2, this)){
			//System.out.println("Collision");
			kill();
			Main.score = Main.score + 100;
		}
		
		AI(ticks);
		
	}
	
	@Override
	public void AI(int ticks){
		
		int[] avoid = new int[1];
		avoid[0] = 2;
		//avoid[1] = 1;
		
		Entity closest = closest(this, avoid, false);
		if(closest == null || dist(closest.pos, this.pos) > 50){
			if(this.pos.x > 350){
				this.vel.x = -200;
			}
			if(this.pos.x < 50){
				this.vel.x = 200;
			}
			
			if(this.vel.x == 0){
				if(rand.nextBoolean()){
					this.vel.x = 200;
				}
				else{
					this.vel.x = -200;
				}
			}
			
			if(this.pos.y < 50){
				this.vel.y = lerp(vel.y, 100, c);
			}
			else if(this.pos.y > 250){
				this.vel.y = lerp(vel.y, -100, c);
			}
			else{
				this.vel.y = lerp(vel.y, 0, c);
			}
		}
		else{
			Vector dir = Vector.add(this.pos, Vector.mult(closest.pos, -1));
			dir = Vector.reduce(dir);
			this.vel = Vector.mult(dir, 100);
		}
		if(ticks - lastShot > nextShot){
			Main.addObject(new EnemyBullet(new Vector(this.pos.x, this.pos.y + width + 5, 0), new Vector(0, 400, 0)));
			nextShot = rand.nextInt(10) + rand.nextInt(20);
			lastShot = ticks;
			//System.out.println("Shot");
		}
		if(pos.x + this.width > 400){
			if(vel.x > 0){
				vel.x = 0;
			}
		}
		
		if(pos.x < 0){
			if(vel.x < 0){
				vel.x = 0;
			}
		}
		
		if(pos.y + this.height > 300){
			if(vel.y > 0){
				vel.y = 0;
			}
		}
		
		if(pos.y < 0){
			if(vel.y < 0){
				vel.y = 0;
			}
		}
		
	}

}