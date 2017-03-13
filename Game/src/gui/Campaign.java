package gui;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import entities.Entity;
import entities.Player;
import main.Main;
import main.Screen;
import main.Vector;
import main.World;

public class Campaign {

	public static List<Entity> objects = new ArrayList<Entity>();
	public static List<Entity> newObjects = new ArrayList<Entity>();
	float screenScale;
	
	boolean doneInit = false;
	
	static World world;
	
	Player player;
	
	
	
	public Campaign(){
		this.screenScale = Main.screenScale;
		world = new World();
		player = new Player(new Vector(200, 150), new Vector(0,0));
	}
	
	public void campaignCycle(){
		if(!doneInit){
			init();
		}
		if(Screen.keys.contains(KeyEvent.VK_Q)){
			Main.campaignActive = false;
			Main.menuActive = true;
			doneInit = false;
			return;
		}
		newObjects.clear();
		for(Entity o : objects){
			newObjects.add(o);
		}
		Graphics2D g = Screen.image.createGraphics();
		g.scale(screenScale, screenScale);
		world.draw(g);
		for(Entity o : objects){
			o.onTick(Main.ticks);
			o.getPos();
			o.draw(g);
		}
		g.dispose();
		Screen.pane.repaint();
		objects.clear();
		for(Entity o : newObjects){
			objects.add(o);
		}
	}
	
	public static void addObject(Entity o){
		int id = 1;
		boolean idTaken = true;
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
		newObjects.add(o);
	}

	private void init(){
		player = new Player(new Vector(200, 200), new Vector(0,0));
		objects.add(player);
		doneInit = true;
	}
	
}
