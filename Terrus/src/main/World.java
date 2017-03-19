package main;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class World {

	Set<Tile> tiles = new HashSet<Tile>();
//	HashMap<String,Zone> map = new HashMap<String,Zone>();
	
	public World(){
//		map.put("00", new Zone(new Vector(0,0)));
	}
	
	public void addTile(Tile t){
		tiles.add(t);
//		Vector vkey = t.pos;
//		vkey.x = Math.floorDiv((int) vkey.x, 100);
//		vkey.y = Math.floorDiv((int) vkey.y, 100);
//		
//		String key = String.valueOf(vkey.x) + String.valueOf(vkey.y);
//		
//		try{
//			map.get(key).addTile(t);
//		} catch (NullPointerException e){
//			map.put(key, new Zone(vkey));
//			map.get(key).addTile(t);
//		}
	}
	
//	public Set<Zone> getZones(Camera c){
//		
//		Set<Zone> z = new HashSet<Zone>();
//		
//		Vector key = c.pos;
//		key.x = Math.floorDiv((int) key.x, 100);
//		key.y = Math.floorDiv((int) key.y, 100);
//		
//		z.add(map.get(key));
//		
//		key = c.pos;
//		key.x = c.pos.x + c.width;
//		key.x = Math.floorDiv((int) key.x, 100);
//		key.y = Math.floorDiv((int) key.y, 100);
//		
//		z.add(map.get(key));
//		
//		key = c.pos;
//		key.y = c.pos.y + c.height;
//		key.x = Math.floorDiv((int) key.x, 100);
//		key.y = Math.floorDiv((int) key.y, 100);
//		
//		key = c.pos;
//		key.x = c.pos.x + c.width;
//		key.y = c.pos.y + c.height;
//		key.x = Math.floorDiv((int) key.x, 100);
//		key.y = Math.floorDiv((int) key.y, 100);
//		
//		z.add(map.get(key));
//		
//		z.add(map.get(key));
//		
//		return z;
//	}
//	
//	public class Zone {
//		
//		Set<Tile> zone = new HashSet<Tile>();
//		int x;
//		int y;
//		
//		public Zone(Vector pos){
//			this.x = (int) pos.x;
//			this.y = (int) pos.y;
//		}
//		
//		public void addTile(Tile t){
//			if(t.pos.x > this.x * 100 && t.pos.x < (this.x + 1) * 100 ){
//				zone.add(t);
//			}
//		}
//		
//	}
	
}
