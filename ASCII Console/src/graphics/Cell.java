package graphics;

import java.awt.Color;

import main.Vector;

public class Cell {

	char text;
	public boolean blank;
	Color c;
	Color textC;
	final Vector pos;
	String tag;
	
	public Cell(Vector pos, Color defaultColor){
		
		this.pos = pos;
		this.blank = true;
		c = defaultColor;
		
	}
	
	public Vector getPos(){return this.pos;}
	
	public int getX(){return this.pos.a;}
	
	public int getY(){return this.pos.b;}
	
	public void setChar(char character){text = character;}
	
	public char getChar(){return text;}
	
	public void clear(){blank = true;}
	
	public void setColor(Color color){this.c = color;}
	
	public Color getColor(){return c;}
	
	public void setTextColor(Color color){this.textC = color;}
	
	public Color getTextColor(){return textC;}
	
	public void setTag(String tag){this.tag = tag;}
	
	public String getTag(){return tag;}
	
}
