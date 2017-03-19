package gui;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import entities.Entity;
import entities.Player;
import main.FileHandler;
import main.Leaderboards;
import main.Main;
import main.Screen;
import main.World;

public class Environment {

	public List<Entity> objects = new ArrayList<Entity>();
	public List<Entity> newObjects = new ArrayList<Entity>();
	
	float screenScale;
	int timer;
	public int killCount;
	boolean doneInit = false;
	
	static World world;
	FileHandler fh;
	Leaderboards lb;
	
	Random rand = new Random();
	
	public static Player player;
	
	public Environment(){
		
		//Get the current window scaling
		this.screenScale = Main.screenScale;
		world = new World();
		
	}
	
	public void cycle(int ticks){
		
		timer++;
		
		//If we haven't initialized our environment, do so
		if(!doneInit){
			init();
		}
		
		checkActionMap(Screen.keys);
		
		//Reset our list of objects to be added to the environment next tick
		newObjects.clear();
		for(Entity o : objects){
			newObjects.add(o);
		}
		
		preDraw();
		
		Graphics2D g = Screen.image.createGraphics();
		//Scale everything we draw by our scale factor
		g.scale(screenScale, screenScale);
		//Draw our background
		world.draw(g);
		
		eventSequence();
		
		draw(g);
		
		//Process entity actions, get their current positions, and render them with our graphics object
		for(Entity o : objects){
			o.onTick(Main.ticks);
			o.getPos();
			o.draw(g);
		}
		
		//Apply our changes to the graphics object and get rid of it
		g.dispose();
		//Repaint our window
		Screen.pane.repaint();
		
		postDraw();
		//Empty our objects list
		objects.clear();
		//Fill it with the new objects added this ticks and ones which we're removed
		for(Entity o : newObjects){
			objects.add(o);
		}
	}

	protected void draw(Graphics2D g) {
		
	}

	protected void init() {
		//Startup Routine
		
	}
	
	protected void exit() {
		//Exit routine
	}

	//Adds an entity to our newObjects list
	public void addObject(Entity o){
		int id = 1;
		boolean idTaken = true;
		//Increment the entity's internal id until we find one that's not taken
		while(idTaken){
			idTaken = false;
			for(Entity q : newObjects){
				if(q.id == id && !idTaken){
					idTaken = true;
					id++;
				}
			}
		}
		o.intId = id;
		//add it to our list
		newObjects.add(o);
		o.env = this;
	}
	
	private void preDraw() {
		//Do things such as checking for the player, ect...
		
	}
	
	private void postDraw() {
		//Maybe useful someday??
		
	}

	protected void eventSequence() {
		// Leave empty for child utilizations
		
	}

	protected void checkActionMap(Set<Integer> s){
	
		//Leave empty for child utilizations
		
	}
	
}
