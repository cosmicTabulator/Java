package entities;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.Matrix;
import main.Vector;

public class EnemyBullet extends Entity{
	
	double theta;
	double v;
	Matrix mult;
	
	public EnemyBullet(Vector o, double th, double v) {
		super(o, new Vector(0,0,0));
		this.id = 4;
		this.melee = true;
		this.width = 3;
		this.height = 5;
		this.theta = th;
		this.v = v;
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
		
		if(pos.y > 300 || pos.y < 0 || pos.x > 400 || pos.x < 0){
			kill();
		}
		
		AI(ticks);
		
	}
	
	@Override
	public void AI(int ticks){
		
		this.vel.y = 1;
		this.vel.x = 0;
		
		this.vel = Vector.mult(Vector.mult(this.vel, mult), v);
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
	
