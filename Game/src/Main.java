import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Main {

	public static Screen screen;
	public boolean running = true;
	static int time;
	int lastTick;
	static int tickCount = 50;
	public static float timePerTick = (float)tickCount/1000;
	static int ticks;
	Draw draw;
	
	static Object ball;
	
	public Main(){
		
		Screen screen = new Screen(400, 300);
		draw = new Draw();
		
		ball = new Object(new Vector(200,0,0),new Vector(0,100,0));
		
//		Font f = new Font(Font.MONOSPACED, Font.PLAIN, 14);
//		
//		draw.g.setFont(f);
//		
//		draw.g.drawString("A@B", 0, 10);
//		draw.g.drawLine(0, 14, 400, 14);
//		draw.g.drawLine(7, 0, 7, 300);
//		draw.g.drawLine(14, 0, 14, 300);
		
		Graphics g = draw.getDraw();
		
		g.drawString("Terrus", 200, 100);
		g.drawString("v 0.0", 200, 200);
		
		g.dispose();
		
		mainLoop(screen);
		
	}
	
	public static void main(String[] args) {
		new Main();

	}
	
	private void mainLoop(Screen screen){
		lastTick = (int) System.currentTimeMillis();
		while(running){
			time = (int) System.currentTimeMillis();
			if (time - lastTick > tickCount){
				lastTick = time;
				tickUpdate(screen);
				ticks++;
				System.out.println(ticks);
			}
		}
	}
	
	public void tickUpdate(Screen screen){
		Graphics g = draw.getDraw();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 400, 300);
		ball.getPos();
		ball.draw(g);
		g.dispose();
		screen.pane.repaint();
		ball.pos.print();
		ball.vel.print();
		
	}

}
