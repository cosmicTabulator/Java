import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

public class Shield extends Entity{

	public Shield(Vector pos) {
		super(pos, new Vector(0,0,0));
		this.id = 7;
		this.width = 16;
		this.height = 3;
		
		try {
		    img = ImageIO.read(getClass().getResource("Textures/Sheild.png"));
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
		
		this.pos.x = Main.player.pos.x;
		this.pos.y = Main.player.pos.y - 10;
		
	}
	
	@Override
	public void AI(int ticks){
		List<Entity> collide = collided(this);
		
		for(Entity o : collide){
			if(o.enemy || o.melee){
				o.kill();
				this.kill();
				Main.player.shield = false;
			}
		}
		
		if(!Main.objects.contains(Main.player)){
			this.kill();
		}
	}

}
