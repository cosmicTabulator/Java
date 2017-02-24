import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static Screen screen;
	public boolean running = true;
	static int time;
	int lastTick;
	static int tickCount = 20;
	public static float fps = (float)tickCount/1000;
	static int ticks;
	static List<Object> objects = new ArrayList<Object>();
	static List<Object> newObjects = new ArrayList<Object>();
	static Player player;
	
	public Main(){
		
		Screen screen = new Screen(400, 300);
		
		player = new Player(new Vector(200,150,0),new Vector(0,0,0));
		
		objects.add(player);
		
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
		newObjects.clear();
		for(Object o : objects){
			newObjects.add(o);
		}
		Graphics g = Screen.image.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 400, 300);
		for(Object o : objects){
			o.onTick(ticks);
			o.getPos();
			o.draw(g);
		}
		g.dispose();
		screen.pane.repaint();
		player.pos.print();
		player.vel.print();
		System.out.println(Screen.keys.toString());
		objects.clear();
		for(Object o : newObjects){
			objects.add(o);
		}
		System.out.println(objects.size());
	}
	
	public static void addObject(Object o){
		newObjects.add(o);
	}

}
