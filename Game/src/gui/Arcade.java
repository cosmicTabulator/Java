package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.json.JSONObject;

import entities.Beamer;
import entities.Dodger;
import entities.Entity;
import entities.Player;
import entities.Seeker;
import entities.Shooter;
import main.FileHandler;
import main.Leaderboards;
import main.Main;
import main.Screen;
import main.Vector;
import main.World;

public class Arcade {

	public static List<Entity> objects = new ArrayList<Entity>();
	public static List<Entity> newObjects = new ArrayList<Entity>();
	
	public static int score = 0;
	public static int highScore = 0;
	boolean scoreSaved = false;
	boolean highscored = false;
	float screenScale;
	int timer;
	int spawnTotal;
	int spawnTime;
	int spawn;
	public static int killCount;
	public static int level;
	boolean spawned = false;
	boolean levelSplash = true;
	boolean doneInit = false;
	
	static World world;
	FileHandler fh;
	Leaderboards lb;
	
	Random rand = new Random();
	
	public static Player player;
	
	public Arcade(){
		
		//Get the current window scaling
		this.screenScale = Main.screenScale;
		world = new World();
		
		//create a new file reader to read all saved scores
		fh = new FileHandler("highscore.json");
		
		//Load read data into a JSON Handler
		JSONObject scores = fh.read();

		//Load JSON data into a score reader
		lb = new Leaderboards(scores);
		
		//Get the highest score found in that file
		highScore = lb.getHighScore();
		
	}
	
	//Called each tick to run the arcade environment
	public void arcadeCycle(){
		//Increment our timer by one
		timer++;
		//If we haven't initialized our environment, do so
		if(!doneInit){
			init();
		}
		//If Q is being pressed, return to the menu and kill the session;
		if(Screen.keys.contains(KeyEvent.VK_Q)){
			Main.arcadeActive = false;
			Main.menuActive = true;
			killSession();
			return;
		}
		//Reset our list of objects to be added to the environment next tick
		newObjects.clear();
		for(Entity o : objects){
			newObjects.add(o);
		}
		//Get a graphics object from our window to render to
		Graphics2D g = Screen.image.createGraphics();
		//Scale everything we draw by our scale factor
		g.scale(screenScale, screenScale);
		//Draw our background
		world.draw(g);
		//If the player has been killed (not in the objects list) save the score, Draw a death screen, and request a re-spawn
		if(!objects.contains(player)){
			//If our score hasn't been saved
			if(!scoreSaved){
				//Save it
				lb.addScore(score);
				fh.write(lb.get());
				scoreSaved = true;
				
			}
			//Don't draw new level screen since we're dead
			levelSplash = false;
			newObjects.clear();
			//Render a death screen with our graphics object
			drawKillScreen(g);
			//If R is pressed, re-spawn the player and reset the game
			if(Screen.keys.contains(KeyEvent.VK_R)){
				player = new Player(new Vector(200,150), new Vector(0,0));
				addObject(player);
				spawned = false;
				level = 0;
				score = 0;
				highscored = false;
				scoreSaved = false;
			}
		}
		//Run our arcade events (Spawning enemies)
		eventSequence();
		//Process entity actions, get their current positions, and render them with our graphics object
		for(Entity o : objects){
			o.onTick(Main.ticks);
			o.getPos();
			o.draw(g);
		}
		//If we've advanced to a new level
		if(levelSplash){
			//Draw a New level banner
			g.setColor(Color.WHITE);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 50));
			world.drawCenter("Level " + level, 150, g);
		}
		//Apply our changes to the graphics object and get rid of it
		g.dispose();
		//Repaint our window
		Screen.pane.repaint();
		//Empty our objects list
		objects.clear();
		//Fill it with the new objects added this ticks and ones which we're removed
		for(Entity o : newObjects){
			objects.add(o);
		}
	}
	
	//Adds an entity to our newObjects list
	public static void addObject(Entity o){
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
	}
	
	//This controls enemy spawning
	private void eventSequence(){
		
		//If our player is still alive
		if(objects.contains(player)){
	
			//If we're on level "0", set various parameters to base values
			if(level == 0){
				spawn = 3;
				spawnTime = 10;
				spawnTotal = 0;
				killCount = 0;
				level++;
				levelSplash = true;
			}
			
			//If our designated spawn time has elapsed and we haven't reached our maximum enemy spawn count
			if(timer > spawnTime && spawnTotal < spawn){
				levelSplash = false;
				//Get a new spawn time based on the current level
				spawnTime = rate(level);
				//Reset our timer;
				timer = 0;
				//Increment our spawn counter
				spawnTotal++;
				//If we're past level 8
				if(level > 8){
					//Let there be a 1/20 chance to spawn a dodger
					if(rand.nextInt(20) > 18){
						addObject(new Dodger(new Vector(rand.nextInt(260) + 60, rand.nextInt(25) + 25)));
					}
					//Otherwise a 2/5 chance to spawn a beamer
					else if(rand.nextInt(10) > 7){
						addObject(new Beamer(new Vector(rand.nextInt(260) + 60, rand.nextInt(25) + 25)));
					}
					//Otherwise a 1/2 chance to spawn a shooter
					else if(rand.nextInt(10) > 4){
						addObject(new Shooter(new Vector(rand.nextInt(260) + 60, rand.nextInt(25) + 25)));
					}
					//And otherwise, spawn a seeker
					else{
						addObject(new Seeker(new Vector(rand.nextInt(260) + 60, 0)));
					}
				}
				//Otherwise, if we're past level 5
				else if(level > 5){
					//Let there be a 1/10 chance to spawn a Beamer
					if(rand.nextInt(10) > 8){
						addObject(new Beamer(new Vector(rand.nextInt(260) + 60, rand.nextInt(25) + 25)));
					}
					//Otherwise, a 1/2 chance to spawn a shooter
					else if(rand.nextInt(10) > 4){
						addObject(new Shooter(new Vector(rand.nextInt(260) + 60, rand.nextInt(25) + 25)));
					}
					//Otherwise spawn a seeker
					else{
						addObject(new Seeker(new Vector(rand.nextInt(260) + 60, 0)));
					}
				}
				//Otherwise, if we've past level 2
				else if(level > 2){
					//Let there be a 1/2 chance to spawn a shooter
					if(rand.nextInt(10) > 4){
						addObject(new Shooter(new Vector(rand.nextInt(260) + 60, rand.nextInt(25) + 25)));
					}
					//Otherwise, spawn a seeker
					else{
						addObject(new Seeker(new Vector(rand.nextInt(260) + 60, 0)));
					}
				}
				//Otherwise, just spawn a seeker
				else{
					addObject(new Seeker(new Vector(rand.nextInt(260) + 60, 0)));
				}
			}
			
			//If the player's killed everything we've spawned
			if(killCount == spawn){
				//Remove any enemy bullets
				for (Entity o: objects){
					if(o.id == 4 || o.enemy){
						o.kill();
					}
				}
				//Reset our counters and increment the level
				killCount = 0;
				level++;
				spawn = spawn(level);
				timer = 0;
				spawnTotal = 0;
				spawnTime = 200;
				levelSplash = true;
			}
		}
	}
	
	//Draw a death screen
	void drawKillScreen(Graphics g){
		g.setColor(Color.WHITE);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
		//If we reached a highscore, say so
		if(score > highScore || highscored){
			highscored = true;
			highScore = score;
			world.drawCenter("New Highscore! " + score, 140, g);
		}
		//Otherwise, just write our score
		else{
			world.drawCenter("Score: " + score, 140, g);
		}
		//Tell the player they've dies and to press r to continue
		g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		world.drawCenter("You Died!", 170, g);
		world.drawCenter("Press R to Respawn", 190, g);
	}

	//Return a number of enemies to spawn as a random linear function of the level
	private int spawn(int level) {

		int out = (int) (level * 2.5) + rand.nextInt(level * 2);
		
		return out;
	}

	//Return a spawn rate as a random function of the level
	private int rate(int level) {
		int out;
		if(level > 7){
			out = rand.nextInt(10) + 10;
		}
		else if(level > 4){
			out = rand.nextInt(15) + 20;
		}
		else{
			out = rand.nextInt(30) + 30;
		}
		
		return out;
	}
	
	//Initialize our environment
	private void init(){
		//Get the all time high score
		highScore = lb.getHighScore();
		//Spawn in the player
		player = new Player(new Vector(200, 200), new Vector(0,0));
		objects.add(player);
		doneInit = true;
	}
	
	//Exit the session
	private void killSession(){
		//Write our current score
		lb.addScore(score);
		fh.write(lb.get());
		//Clear our objects list
		objects.clear();
		//Reset the score
		score = 0;
		//Make sure to initialize then next time we start playing
		doneInit = false;
	}
	
}
