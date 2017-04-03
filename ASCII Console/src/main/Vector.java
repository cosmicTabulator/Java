package main;

public class Vector {

	public int a;
	public int b;
	
	public Vector(int a, int b){
		this.a = a;
		this.b = b;
	}
	
	public int hashCode(){
		
		int hash = (a + "," + b).hashCode();
		
		return hash;
		
	}
	
}
