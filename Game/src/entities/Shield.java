package entities;
import java.awt.Graphics2D;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import gui.Arcade;
import main.Vector;

public class Shield extends Entity{

	public Shield(Vector pos) {
		super(pos, new Vector(0,0));
		this.id = 7;
		this.width = 16;
		this.height = 3;
		
		try {
		    img = ImageIO.read(getClass().getResource("/Textures/Shield.png"));
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
	public void getPos() {
		
		this.pos.x = (int)(Arcade.player.pos.x - ((this.width-Arcade.player.width)/2));
		this.pos.y = Arcade.player.pos.y - 10;
		
	}
	
	@Override
	public void AI(int ticks){
		List<Entity> collide = collided(this);
		
		for(Entity o : collide){
			if(o.enemy || o.melee && o.id != 8){
				o.kill();
				this.kill();
				Arcade.player.shield = false;
			}
		}
		
		if(!Arcade.objects.contains(Arcade.player)){
			this.kill();
		}
	}

}
