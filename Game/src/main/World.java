package main;
import java.awt.Color;
import java.awt.Graphics;

import gui.Arcade;

public class World {

	//g.getFontMetrics().getStringWidth(str);
	
	int width;
	String str = new String();
	
	public void draw(Graphics g){
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 400, 300);
		g.setColor(Color.WHITE);
		String sNum = Integer.toString(Arcade.score);
		g.drawString(sNum, 390 - g.getFontMetrics().stringWidth(sNum), 20);
		if(Arcade.player.shieldUse){
			g.drawString("Shield Available!", 20, 20);
		}
	}
	
	void drawCenter(String str, int height, Color c, Graphics g){
		
		int width = g.getFontMetrics().stringWidth(str);
		g.setColor(c);
		g.drawString(str, 200 - width/2, height);
		
	}
	
	public void drawCenter(String str, int height, Graphics g){
		
		int width = g.getFontMetrics().stringWidth(str);
		g.setColor(Color.WHITE);
		g.drawString(str, 200 - width/2, height);
		
	}
	
}
