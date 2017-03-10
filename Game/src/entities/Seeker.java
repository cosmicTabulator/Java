package entities;
import java.io.IOException;

import javax.imageio.ImageIO;

import gui.Arcade;
import main.Main;
import main.Vector;

public class Seeker extends Entity{

	int spawnTime;
	int speed = 100;
	
	public Seeker(Vector pos) {
		super(pos, new Vector(0,0,0));
		this.id = 5;
		this.melee = true;
		this.enemy = true;
		spawnTime = Main.ticks;
		
		try {
		    img = ImageIO.read(getClass().getResource("/Textures/Enemy1.png"));
		} catch (IOException e) {
			System.out.println(this);
			System.out.println(e);
		}
	}
	
	@Override
	public void AI(int ticks){
		if(Arcade.level > 3){
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
			Arcade.score = Arcade.score + 10;
		}
	}
}
