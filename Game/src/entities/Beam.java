package entities;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.Vector;

public class Beam extends Entity{

	Beamer parent;
	
	public Beam(Vector pos, Beamer b) {
		super(pos, new Vector(0,0));
		
		this.id = 8;
		this.melee = true;
		this.width = 3;
		this.height = 300;
		this.parent = b;
		
		try {
		    img = ImageIO.read(getClass().getResource("/Textures/Beam.png"));
		} catch (IOException e) {
			System.out.println(this);
			System.out.println(e);
		}
		
	}
	
	@Override
	public void getPos(){
		
		this.pos.x = (int)(parent.pos.x + parent.width/2 - 1);
		this.pos.y = (int)(parent.pos.y + parent.height);
		
	}
	
	@Override
	public void draw(Graphics2D g){
		AffineTransform identity = new AffineTransform();
		AffineTransform trans = new AffineTransform();
		trans.setTransform(identity);
		trans.setToTranslation(pos.x, pos.y);
		trans.rotate(Math.toRadians(rot));
		g.drawImage(img, trans, null);
	}
	
	@Override
	public void AI(int ticks){
		if(!this.parent.shooting || this.parent.dead){
			this.kill();
		}
	}

}
