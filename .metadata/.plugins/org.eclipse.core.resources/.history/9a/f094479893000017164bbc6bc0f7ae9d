import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

public class Shield extends Entity{

	public Shield(Vector pos) {
		super(pos, new Vector(0,0,0));
		this.id = 7;
		this.width = 16;
		this.height = 2;
		
	}
	
	@Override
	public void draw(Graphics g){
		
		g.setColor(Color.RED);
		g.fillRect((int)(this.pos.x - (this.width-Main.player.width)/2), (int)this.pos.y, this.width, this.height);
		
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
