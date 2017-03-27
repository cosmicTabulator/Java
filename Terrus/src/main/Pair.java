package main;

public class Pair {

	int x;
	int y;
	
	public Pair(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	@Override
	public int hashCode(){
		
		int hash = 0;
		
		String s = x + ":" + y;
		
		hash = s.hashCode();
		
		return hash;
		
	}
	
}
