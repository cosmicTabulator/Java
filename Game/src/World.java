import java.awt.Color;
import java.awt.Graphics;

public class World {

	//g.getFontMetrics().getStringWidth(str);
	
	int width;
	String str = new String();
	
	public void draw(Graphics g){
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 400, 300);
		g.setColor(Color.WHITE);
		drawCenter(Integer.toString(Main.score), 50, g);
	}
	
	void drawCenter(String str, int height, Graphics g){
		
		int width = g.getFontMetrics().stringWidth(str);
		g.drawString(str, 200 - width/2, height);
		
	}
	
}