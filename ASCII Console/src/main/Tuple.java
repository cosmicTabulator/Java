package main;

public class Tuple {

	public int a;
	public int b;
	
	public Tuple(int a, int b){
		this.a = a;
		this.b = b;
	}
	
	public int hashCode(){
		
		int hash = (a + "," + b).hashCode();
		
		return hash;
		
	}
	
}
