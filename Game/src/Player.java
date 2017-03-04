import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Set;

import javax.imageio.ImageIO;

public class Player extends Entity{

	int lastSpawn = 0;
	int lastSpawnEnemy;
	int fireRate = 5;
	boolean shieldUse = false;
	boolean shield = false;
	
	public Player(Vector pos, Vector vel) {
		super(pos, vel);
		this.id = 1;
		this.intId = 0;
		this.c = 0.1f;
		this.v = 100;
		
		try {
		    img = ImageIO.read(getClass().getResource("Textures/Ship1.png"));
		} catch (IOException e) {
			System.out.println(this);
			System.out.println(e);
		}
		
	}

	@Override
	public void checkActionMap(Set<Integer> s){
		
		if(s.contains(KeyEvent.VK_A)){
			vel.x = -v;
		}
		else if(s.contains(KeyEvent.VK_D)){
			vel.x = v;
		}
		else{
			vel.x = lerp(vel.x, 0, c);
		}
		
		if(s.contains(KeyEvent.VK_W)){
			vel.y = -v;
		}
		else if (s.contains(KeyEvent.VK_S)){
			vel.y = v;
		}
		else{
			vel.y = lerp(vel.y, 0, c);
		}
		
		if(s.contains(KeyEvent.VK_F) && !shield && shieldUse){
			
			Main.addObject(new Shield(new Vector(pos.x, pos.y, 0)));
			shield = true;
			shieldUse = false;
			
		}
		
		if(s.contains(KeyEvent.VK_SPACE) && ticks - lastSpawn > fireRate){
			Main.addObject(new Bullet (new Vector(pos.x + (width/2), pos.y, 0), new Vector(0, -400, 0)));
			lastSpawn = ticks;
		}
		
	}
	
	@Override
	public void draw(Graphics2D g){
		g.drawImage(this.img, (int)this.pos.x, (int)this.pos.y, width, height, null);
	}
	
	@Override
	public void onTick(int ticks){

		this.ticks = ticks;
		
		checkActionMap(Screen.keys);
		
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
		
		for(Entity o : collided(this)){
			if(o.melee && !shield){
				kill();
				break;
			}
		}
	}
}

