package main;

public class Vector {

	public float x;
	public float y;
	public float z;
	
	public Vector(float x, float y, float z){
		
		this.x = x;
		this.y = y;
		this.z = z;
		
	}
	
	public static Vector add(Vector a, Vector b){
		
		float x = a.x + b.x;
		float y = a.y + b.y;
		float z = a.z + b.z;
		
		Vector c = new Vector(x, y, z);
		
		return c;
	}
	
	public static Vector mult(Vector a, float n){
		
		float x = a.x * n;
		float y = a.y * n;
		float z = a.z * n;
		
		Vector c = new Vector(x,y,z);
		return c;
	}
	
	public Vector cross(Vector a, Vector b){
		
		//TODO Cross Products
		// Default returns empty vector
		
		Vector c = new Vector(0,0,0);
		
		return c;
	}
	
	public Vector dot(Vector a, Vector b){
		
		//TODO Dot Products
		//Default returns empty vector
		
		Vector c = new Vector(0,0,0);
		
		return c;
	}
	
	public void print(){
		System.out.println(this.x + ", " + this.y + ", " + this.z);
	}
	
	public static Vector reduce(Vector a){
		
		//WARNING! DOES NOT WORK WITH Z
		
		Vector r;
		if(a.x == 0 || a.y == 0){
			if(a.x != 0){
				r = new Vector(1,0,0);
			}
			else if (a.y != 0){
				r = new Vector(0,1,0);
			}
			else{
				r = new Vector(0,0,0);
			}
		}
		else if(Math.abs(a.x) > Math.abs(a.y)){
			r = Vector.mult(a, Math.abs(1/a.x));
		}
		else{
			r = Vector.mult(a, Math.abs(1/a.y));
		}
		
		return r;
	}
}