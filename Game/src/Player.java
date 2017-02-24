import java.awt.event.KeyEvent;
import java.util.Set;

public class Player extends Object{

	int lastSpawn = 0;
	int fireRate = 10;
	
	public Player(Vector pos, Vector vel) {
		super(pos, vel);
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
		
		if(s.contains(KeyEvent.VK_SPACE) && ticks - lastSpawn > fireRate){
			Main.addObject(new Bullet (new Vector(pos.x, pos.y, 0), new Vector(0, -100, 0)));
			lastSpawn = ticks;
		}
	}
	
}
