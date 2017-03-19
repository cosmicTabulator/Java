package entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.util.Random;
import java.util.Set;

import javax.imageio.ImageIO;

import gui.Arcade;
import gui.Campaign;
import main.Vector;

public class Boss1 extends Entity{

	Vector playerPos;
	
	private int lastSpawn;
	private int fireRate = 5;
	private double theta = Math.PI/4;
	boolean fire = true;
	double dir = 0.2;
	double startAngle;
	Random rand = new Random();
	public int hp = 100;
	private int mode;
	private int timer;
	
	public Boss1(Vector pos) {
		super(pos, new Vector(0,0));
		
		try {
		    img = ImageIO.read(getClass().getResource("/Textures/Enemy2.png"));
		} catch (IOException e) {
			System.out.println(this);
			System.out.println(e);
		}
		
	}

	@Override
	public void draw(Graphics2D g){
		AffineTransform identity = new AffineTransform();
		AffineTransform trans = new AffineTransform();
		trans.setTransform(identity);
		trans.setToTranslation(pos.x, pos.y);
		trans.rotate(Math.toRadians(rot));
		g.drawImage(img, trans, null);
		g.setColor(Color.RED);
		g.fillRect((int)this.pos.x - hp/2 + width/2, (int)this.pos.y - 10, hp, 5);
	}
	
	@Override
	public void AI(int ticks){
		
		timer++;
		
		if(hp < 0 || getPlayer() == null){
			kill();
		}
		
		if(ticks - lastSpawn > fireRate){
		
			//theta = rand.nextDouble() * Math.PI * 2;
		
			//If there's still a player
			if(getPlayer() != null){
				playerPos = getPlayer().pos;
				//Get a vector cossosponding to the difference in position between this entity and the player
				Vector dir = Vector.add(Vector.mult(this.pos, -1), playerPos);

				theta = Math.atan2(-dir.x,dir.y);
				
			}
			
			if(rand.nextBoolean()){
				theta = theta + (rand.nextDouble() * (Math.PI/8));
			} else {
				theta = theta - (rand.nextDouble() * (Math.PI/8));
			}
			
			env.addObject(new EnemyBullet(pos, theta, 200));
				
			lastSpawn = ticks;
		}
		for(Entity o : collided(this)){
			if(o.id == 2){
				o.kill();
				hp--;
			}
		}
		
	}
	
}
