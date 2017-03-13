package entities;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.Matrix;
import main.Vector;

public class PhaseBullet extends Entity{

	private int localTime;
	private double theta;
	private Matrix mult;
	
	public PhaseBullet(Vector o, double th) {
		super(o, new Vector(0,0));
		this.id = 4;
		this.melee = true;
		this.width = 3;
		this.height = 5;
		this.theta = th;
		localTime = 0;
		mult = new Matrix(Math.cos(theta), -Math.sin(theta), Math.sin(theta), Math.cos(theta));
		
		try {
		    img = ImageIO.read(getClass().getResource("/Textures/Bullet2.png"));
		} catch (IOException e) {
			System.out.println(this);
			System.out.println(e);
		}
	}

	@Override
	public void onTick(int ticks){
		
		rot = - Math.atan2(vel.x,vel.y);
		
		this.ticks = ticks;
		
		AI(ticks);
		
	}
	
	@Override
	public void AI(int ticks){
		localTime++;
//		this.vel.y = (float) (localTime * Math.cos((float)localTime/100) + Math.sin((float)localTime/100));
//		this.vel.x = (float) (Math.cos((float)localTime/100) - localTime * Math.sin((float)localTime/100));

		this.vel.y = 100 * Math.abs(Math.sin((float)localTime/10));
		this.vel.x = 0;
		
		vel = Vector.mult(vel, mult);
		
//		vel.print();
		
//		pos.x = (float) (Math.exp((float)localTime/100) * Math.cos((float)localTime/100)) + origin.x;
//		pos.y = (float) (Math.exp((float)localTime/100) * Math.sin((float)localTime/100)) + origin.y;
		
//		pos.x = (float) (localTime * Math.cos((float)localTime/100)) + origin.x;
//		pos.y = (float) (localTime * Math.sin((float)localTime/100)) + origin.y;
		
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
