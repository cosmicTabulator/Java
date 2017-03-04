import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class World {

	//g.getFontMetrics().getStringWidth(str);
	
	int width;
	String str = new String();
	
	public void draw(Graphics g){
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 400, 300);
		g.setColor(Color.WHITE);
		drawCenter(Main.score, 50, g);
		if(!Main.objects.contains(Main.player)){
			g.setColor(Color.WHITE);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
			drawCenter("You Died!", 170, g);
			drawCenter("Press R to Respawn", 190, g);
			//g.drawImage(img, x, y, width, height, observer)
		}
	}
	
	void drawCenter(String str, int height, Graphics g){
		
		int width = g.getFontMetrics().stringWidth(str);
		g.drawString(str, 200 - width/2, height);
		
	}
	
}
