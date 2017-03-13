package entities;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.io.IOException;

import javax.imageio.ImageIO;

import gui.Arcade;
import main.Vector;

public class Beamer extends Entity{

	int timer;
	int frequency;
	boolean shooting = false;
	boolean dead = false;
	
	public Beamer(Vector pos) {
		super(pos, new Vector(0,0,0));
		
		this.id = 7;
		this.enemy = true;
		
		try {
		    img = ImageIO.read(getClass().getResource("/Textures/Enemy4.png"));
		} catch (IOException e) {
			System.out.println(this);
			System.out.println(e);
		}
	}
	
	@Override
	public void draw(Graphics2D g){
		AffineTransform identity = new AffineTransform();
		AffineTransform trans = new AffineTransform();
		trans.setTransform(identity);
		trans.setToTranslation(pos.x, pos.y);
		trans.rotate(Math.toRadians(rot));
		g.drawImage(img, trans, null);
		
		if(timer > 65 && !shooting){
			g.setColor(Color.PINK);
			g.setStroke(new BasicStroke(0.5f));
			g.drawLine((int)(pos.x + this.width/2), (int)(pos.y + this.height), (int)(pos.x + this.width/2 -1), (int)(pos.y + this.height + 500));
		}
	}
	
	@Override
	public void AI(int ticks){
		timer++;
		
		if(getPlayer() != null){
			if(Arcade.level > 9){
				frequency = 80;
			} else{
				frequency = 120;
			}

			if(timer < frequency && !shooting){
				vel.y = (float) Math.sin((float)ticks/10) * 50;
				this.c = 0.01f;
				if(getPlayer().pos.x > this.pos.x){
					vel.x = lerp(vel.x, 200, c);
				}
				else{
					vel.x = lerp(vel.x, -200, c);
				}
			} else if(timer < 50 && shooting){
				vel.y = 0;
				this.c = 0.05f;
				if(getPlayer().pos.x > this.pos.x){
					vel.x = lerp(vel.x, 50, c);
				}
				else{
					vel.x = lerp(vel.x, -50, c);
				}
			} else if(shooting){
				shooting = false;
				timer = 0;
			} else{
				shooting = true;
				Arcade.addObject(new Beam(new Vector((int)(pos.x + this.width/2 - 1), (int)(pos.y + this.height), 0), this));
				timer = 0;
			}
		}
		
		if(isCollided(2, this)){
			kill();
			this.dead = true;
			Arcade.score = Arcade.score + 40;
		}

	}

}
