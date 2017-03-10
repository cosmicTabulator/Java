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
	
	static World world;
	FileHandler fh;
	Leaderboards lb;
	
	Random rand = new Random();
	
	public static Player player;
	
	public Arcade(){
		
		this.screenScale = Main.screenScale;
		world = new World();
		
		fh = new FileHandler("highscore.json");
		
		JSONObject scores = fh.read();

		lb = new Leaderboards(scores);
		
		highScore = lb.getHighScore();
		
		player = new Player(new Vector(200, 200, 0), new Vector(0,0,0));
		
		objects.add(player);
		
	}
	
	public void arcadeCycle(){
		timer++;
		if(Screen.keys.contains(KeyEvent.VK_Q)){
			Main.arcadeActive = false;
			Main.menuActive = true;
			objects.remove(player);
		}
		newObjects.clear();
		for(Entity o : objects){
			newObjects.add(o);
		}
		Graphics2D g = (Graphics2D) Screen.image.getGraphics();
		g.scale(screenScale, screenScale);
		world.draw(g);
		if(!objects.contains(player)){
			if(!scoreSaved){
				
				lb.addScore(score);
				fh.write(lb.get());
				scoreSaved = true;
				
			}
			levelSplash = false;
			newObjects.clear();
			drawKillScreen(g);
			if(Screen.keys.contains(KeyEvent.VK_R)){
				player = new Player(new Vector(200,150,0), new Vector(0,0,0));
				addObject(player);
				spawned = false;
				level = 0;
				score = 0;
				highscored = false;
				scoreSaved = false;
			}
		}
		eventSequence();
		for(Entity o : objects){
			o.onTick(Main.ticks);
			o.getPos();
			o.draw(g);
		}
		if(levelSplash){
			g.setColor(Color.WHITE);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 50));
			world.drawCenter("Level " + level, 150, g);
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
	
	private void eventSequence(){
		
		if(objects.contains(player)){
	
			if(level == 0){
				spawn = 3;
				spawnTime = 10;
				spawnTotal = 0;
				killCount = 0;
				level++;
				levelSplash = true;
			}
			
			if(timer > spawnTime && spawnTotal < spawn){
				levelSplash = false;
				spawnTime = rate(level);
				timer = 0;
				spawnTotal++;
				if(level > 8){
					if(rand.nextInt(20) > 18){
						addObject(new Dodger(new Vector(rand.nextInt(260) + 60, rand.nextInt(25) + 25, 0)));
					}
					else if(rand.nextInt(10) > 7){
						addObject(new Beamer(new Vector(rand.nextInt(260) + 60, rand.nextInt(25) + 25, 0)));
					}
					else if(rand.nextInt(10) > 4){
						addObject(new Shooter(new Vector(rand.nextInt(260) + 60, rand.nextInt(25) + 25, 0)));
					}
					else{
						addObject(new Seeker(new Vector(rand.nextInt(260) + 60, 0, 0)));
					}
				}
				else if(level > 5){
					if(rand.nextInt(10) > 8){
						addObject(new Beamer(new Vector(rand.nextInt(260) + 60, rand.nextInt(25) + 25, 0)));
					}
					else if(rand.nextInt(10) > 4){
						addObject(new Shooter(new Vector(rand.nextInt(260) + 60, rand.nextInt(25) + 25, 0)));
					}
					else{
						addObject(new Seeker(new Vector(rand.nextInt(260) + 60, 0, 0)));
					}
				}
				else if(level > 2){
					if(rand.nextInt(10) > 4){
						addObject(new Shooter(new Vector(rand.nextInt(260) + 60, rand.nextInt(25) + 25, 0)));
					}
					else{
						addObject(new Seeker(new Vector(rand.nextInt(260) + 60, 0, 0)));
					}
				}
				else{
					addObject(new Seeker(new Vector(rand.nextInt(260) + 60, 0, 0)));
				}
			}
			
			if(killCount == spawn){
				for (Entity o: objects){
					if(o.id == 4 || o.enemy){
						o.kill();
					}
				}
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
	
	void drawKillScreen(Graphics g){
		g.setColor(Color.WHITE);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
		if(score > highScore || highscored){
			highscored = true;
			highScore = score;
			world.drawCenter("New Highscore! " + score, 140, g);
		}
		else{
			world.drawCenter("Score: " + score, 140, g);
		}
		g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		world.drawCenter("You Died!", 170, g);
		world.drawCenter("Press R to Respawn", 190, g);
	}

	private int spawn(int level) {

		int out = (int) (level * 2.5) + rand.nextInt(level * 2);
		
		return out;
	}

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
	
}
