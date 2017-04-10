package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public class Tile {

	int width;
	int height;
	float temp;
	float tempBuffer;
	boolean isPlayer;
	Vector pos;
	Vector screenPos;
	Color color;
	
	public Tile(Vector pos){
		this.pos = pos;
		this.screenPos = Vector.mult(pos, 10);
		width = 10;
		height = 10;
		color = Color.WHITE;
	}
	
	public void draw(Graphics2D g, Vector offset){
		this.screenPos = Vector.mult(pos, 10);
		if(this.isPlayer){
			offset.print();
			screenPos.print();
		}
		g.setColor(color);
		g.fillRect((int)(screenPos.x + offset.x), (int)(screenPos.y + offset.y), width, height);
	}

	public boolean inViewRange(Camera c) {
		
		boolean out = false;
		
		if(this.pos.x >= c.pos.x && this.pos.x <= c.pos.x + c.width){
			if(this.pos.y >= c.pos.y && this.pos.y <= c.pos.y + c.height){
				out = true;
			}
		}
		
		return out;
	}
	
	private List<Tile> getNeighbors(){
		
		List<Tile> neighbors = new ArrayList<Tile>();
		
		Vector coordN = new Vector(this.pos.x,this.pos.y - 1);
		Vector coordS = new Vector(this.pos.x,this.pos.y + 1);
		Vector coordE = new Vector(this.pos.x + 1,this.pos.y);
		Vector coordW = new Vector(this.pos.x - 1,this.pos.y);
		
		neighbors.add(Main.world.tiles.get(coordN.hashCode()));
		neighbors.add(Main.world.tiles.get(coordS.hashCode()));
		neighbors.add(Main.world.tiles.get(coordE.hashCode()));
		neighbors.add(Main.world.tiles.get(coordW.hashCode()));
		
		return neighbors;
		
	}
	
	public Tile neighbor(int x, int y){
		
		Vector coord = new Vector((int)this.pos.x - x,(int)this.pos.y - y);
		
		return Main.world.tiles.get(coord.hashCode());
	}
	
	public void simulate(){

		//Heat Transfer Later
		
	}
	
	public void updateReferences(Vector lastPos){
		
		Main.world.tiles.remove(lastPos.hashCode());
		Main.world.tiles.put(this.pos.hashCode(), this);
		
	}
	
}
