package gui;

import java.awt.event.KeyEvent;
import java.util.Set;

import entities.Player;
import main.Main;
import main.Vector;

public class Campaign extends Environment{
	
	public Campaign(){
		super();
	}
	
	@Override
	protected void init(){
		player = new Player(new Vector(200, 150), new Vector(0,0));
		objects.add(player);
		player.env = this;
		doneInit = true;
	}
	
	@Override
	protected void exit(){
		
		objects.clear();
		doneInit = false;
		
	}
	
	@Override
	protected void checkActionMap(Set<Integer> s){
		//If Q is being pressed, return to the menu and kill the session;
		if(s.contains(KeyEvent.VK_Q)){
			Main.campaignActive = false;
			Main.menuActive = true;
			exit();
			return;
		}
		if(s.contains(KeyEvent.VK_R) && !objects.contains(player)){
			System.out.println("R");
			player = new Player(new Vector(200, 150), new Vector(0,0));
			
			objects.add(player);
			player.env = this;
		}
	}
	
}
