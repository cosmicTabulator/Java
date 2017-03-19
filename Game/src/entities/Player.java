package entities;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Set;

import javax.imageio.ImageIO;

import gui.Campaign;
import main.Main;
import main.Screen;
import main.Vector;

public class Player extends Entity{

	int lastSpawn = 0;
	int lastSpawnEnemy;
	int fireRate = 5;
	public boolean shieldUse = false;
	boolean shield = false;
	
	public Player(Vector pos, Vector vel) {
		super(pos, vel);
		this.id = 1;
		this.intId = 0;
		this.c = 1;
		this.v = 100;
		
		//Get the player texture from the source files
		try {
		    img = ImageIO.read(getClass().getResource("/Textures/Ship1.png"));
		} catch (IOException e) {
			System.out.println(this);
			System.out.println(e);
		}
		
	}

	@Override
	public void checkActionMap(Set<Integer> s){
		
		//Map A and D to horizontal motion
		if(s.contains(KeyEvent.VK_A)){
			vel.x = -v;
		}
		else if(s.contains(KeyEvent.VK_D)){
			vel.x = v;
		}
		else{
			vel.x = lerp(vel.x, 0, c);
		}
		
		//Map W and S to vertical motion
		if(s.contains(KeyEvent.VK_W)){
			vel.y = -v;
		}
		else if (s.contains(KeyEvent.VK_S)){
			vel.y = v;
		}
		else{
			vel.y = lerp(vel.y, 0, c);
		}
		
		//Map E to spawn the boss, only during campaign (this is for testing purposes)
		if(s.contains(KeyEvent.VK_E) && ticks - lastSpawnEnemy > 20 && Main.campaignActive){
			env.addObject(new Boss1(new Vector(pos.x, pos.y - 50)));
			lastSpawnEnemy = ticks;
		}
		
		//Map F to activate the shield if the player has one pending
		if(s.contains(KeyEvent.VK_F) && !shield && shieldUse){
			
			env.addObject(new Shield(new Vector(pos.x, pos.y)));
			shield = true;
			shieldUse = false;
			
		}
		
		//Map Space to fire bullets (During Campaign)
		if(s.contains(KeyEvent.VK_SPACE) && ticks - lastSpawn > fireRate){
			env.addObject(new Bullet (new Vector(pos.x + (width/2), pos.y), new Vector(0, -400)));
			lastSpawn = ticks;
		}
		
	}
	
	@Override
	public void onTick(int ticks){

		this.ticks = ticks;
		
		checkActionMap(Screen.keys);
		
		//Prevent the player from having a velocity that would move them outside the screen
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
		
		//If the player is collided with an entity with melee enabled and doesn't have a shield activated, kill the player
		for(Entity o : collided(this)){
			if(o.melee && !shield){
				kill();
				break;
			}
		}
	}
}

