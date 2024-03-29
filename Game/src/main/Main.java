package main;
import gui.Arcade;
import gui.Campaign;
import gui.Menu;

/*
 * Created by cosmicTabulator (cT)
 * Tuesday March 7, 2017
 * 
 * 
 * V0.2
 * +Added a menu screen
 * +Added a Campaign Mode
 * +Added a first Boss
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
	public static boolean campaignActive = false;
	static int tickCount = 20;
	public static float fps = (float)tickCount/1000;
	public static int ticks;
	static int stageTicks = 0;
	boolean paused = false;
	float pausePressed;

	Menu menu;
	Arcade arcade;
	Campaign campaign;
	
	public Main(){
		
		//Create a New INstance of our screen to render our game to
		screen = new Screen((int)(400*screenScale), (int)(300*screenScale));
		
		//Create new Instances of Menu, Arcade, and Campaign environments to interact with
		menu = new Menu();

		arcade = new Arcade();
		
		campaign = new Campaign();
		
		//Start the main loop using our created screen as the render target
		mainLoop(screen);
	}
	
	public static void main(String[] args) {
		new Main();

	}
	
	//Continuously loops through this section, running the main aspects of the game
	private void mainLoop(Screen screen){
		//Sets the starting time to the surrent system time
		lastTick = (int) System.currentTimeMillis();
		while(true){
			//gets the system time
			time = (int) System.currentTimeMillis();
			//if enough time has passed since the last tick update, do another one
			if (time - lastTick > tickCount){
				lastTick = time;
				tickUpdate(screen);
				//Increment our tick counter
				ticks++;
				stageTicks++;
			}
		}
	}
	
	public void tickUpdate(Screen screen){

		if(arcadeActive){
			//If the arcade is currently active, render it
			arcade.cycle(ticks);
		} else if(menuActive){
			//If the menu is currently active, render that
			menu.menuCycle();
		} else if (campaignActive){
			//if the campaign is currently active, render it
			//Note, Campaign is currently very unfinished, you can ignore it
			campaign.cycle(ticks);
		}
	}

}
