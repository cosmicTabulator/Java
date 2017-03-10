package main;
import gui.Arcade;
import gui.Menu;
import main.Tree.Node;

/*
 * Created by cosmicTabulator (cT)
 * Tuesday March 7, 2017
 * 
 * 
 * V0.2
 * +Added a menu screen
 * *Rewrote internal structure
 * *Increased Beamer score to 30
 * 
 * 
 * ~I have no idea what I'm doing
 * 
 */

public class Main {
	
	public static final String version = "0.2";
	

	public static Screen screen;
	public static float screenScale = 2;
	static int time;
	int lastTick;

	public static boolean arcadeActive = false;
	public static boolean menuActive = true;
	static int tickCount = 20;
	public static float fps = (float)tickCount/1000;
	public static int ticks;
	static int stageTicks = 0;
	boolean paused = false;
	float pausePressed;

	Menu menu;
	Arcade arcade;
	
	public Main(){
		
		screen = new Screen((int)(400*screenScale), (int)(300*screenScale));
		
		menu = new Menu();

		arcade = new Arcade();
		
		//mainLoop(screen);
		
		Tree<Integer> tree = new Tree<Integer>(0);
		tree.root.addChild(3);
		tree.root.addChild(4);
		tree.search(3);
	}
	
	public static void main(String[] args) {
		new Main();

	}
	
	private void mainLoop(Screen screen){
		lastTick = (int) System.currentTimeMillis();
		while(true){
			time = (int) System.currentTimeMillis();
//			if(Screen.keys.contains(KeyEvent.VK_ESCAPE) && !paused){
//				paused = true;
//				pausePressed = System.currentTimeMillis();
//			}
//			if(Screen.keys.contains(KeyEvent.VK_ESCAPE) && paused){
//				paused = false;
//			}
			if (time - lastTick > tickCount){
				lastTick = time;
				tickUpdate(screen);
				ticks++;
				stageTicks++;
				//System.out.println(ticks);
			}
		}
	}
	
	public void tickUpdate(Screen screen){
		if(arcadeActive){
			arcade.arcadeCycle();
		} else if(menuActive){
			menu.menuCycle();
		}
	}

}
