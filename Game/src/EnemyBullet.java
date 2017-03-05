import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.io.IOException;

import javax.imageio.ImageIO;

public class EnemyBullet extends Entity{
	
	public EnemyBullet(Vector pos, Vector vel) {
		super(pos, vel);
		this.id = 4;
		this.melee = true;
		this.width = 3;
		this.height = 5;
		
		try {
		    img = ImageIO.read(getClass().getResource("Textures/Bullet2.png"));
		} catch (IOException e) {
			System.out.println(this);
			System.out.println(e);
		}
	}

	@Override
	public void onTick(int ticks){
		
//		if(vel.x != 0 && vel.y > 0){
//			rot = Math.atan(vel.y/vel.x);
//		}
//		else if(vel.x != 0 && vel.y < 0){
//			rot = Math.atan(vel.y/vel.x);
//			if(vel.x > 0){
//				rot = rot + Math.PI;
//			}
//			else{
//				rot = rot - Math.PI;
//			}
//		}
//		else{
//			if(-vel.y > 0){
//				rot = Math.PI/2;
//			}
//			else{
//				rot = -Math.PI/2;
//			}
//		}
//		
		
		rot = - Math.atan2(vel.x,vel.y);
		
		this.ticks = ticks;
		
		if(pos.y > 300 || pos.y < 0 || pos.x > 400 || pos.x < 0){
			kill();
		}
		
	}
	
	@Override
	public void draw(Graphics2D g){
		
		AffineTransform identity = new AffineTransform();
		AffineTransform trans = new AffineTransform();
		trans.setTransform(identity);
		trans.setToTranslation(pos.x, pos.y);
		trans.rotate(rot);
		g.drawImage(img, trans, null);

		}
	}
	
