package main;

import java.util.ArrayList;
import java.util.List;

public class Vector {

	double x;
	double y;
	
	public Vector(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public static Vector midpoint(Vector a, Vector b){
		
		Vector out = new Vector(0,0);
		
		out.x = ( a.x + b.x )/2;
		out.y = ( a.y + b.y )/2;
		
		return out;
	}

	public static List<Vector> interpolate(List<Vector> l){
		
		long size = Math.round(l.get(l.size()-1).x);
		
		List<Vector> out = new ArrayList<Vector>();
		
		for(int i = (int) Math.round(l.get(0).x); i < size; i++){
			
			Vector pre = searchFor(l, i);
			Vector post = searchFor(l, i+1);
			
			double m = (post.y - pre.y)/(post.x - pre.x);
			double b = pre.y - (pre.x * m);
			
			out.add(new Vector(i, (int) Math.round(b + i*m)));
		}
		
		return out;
	}
	
	public static Vector searchFor(List<Vector> l, int v){
		
		Vector closest = new Vector(0,0);
		double dist = Double.MAX_VALUE;
		
		for(Vector p : l){
			
			if(Math.abs(p.x-v) < dist && p.x <= v){
				closest = p;
				dist = Math.abs(p.x-v);
			}
			
		}
		
		return closest;
	}
	
	public void print(){
		
		System.out.println(this.x + "," + this.y);
		
	}
	
	public static Vector add(Vector a, Vector b){
		
		double x = a.x + b.x;
		double y = a.y + b.y;
		
		Vector c = new Vector(x, y);
		
		return c;
	}
	
	public static Vector mult(Vector a, double n){
		
		double x = a.x * n;
		double y = a.y * n;
		
		Vector c = new Vector(x,y);
		return c;
	}
	
	public static Vector mult(Vector v, Matrix m){
		
		Vector out = new Vector(0,0);
		
		out.x = v.x * m.a + v.y * m.b;
		out.y = v.x * m.c + v.y * m.d;
		
		return out;
		
	}
	
	public Vector cross(Vector a, Vector b){
		
		//TODO Cross Products
		// Default returns empty vector
		
		Vector c = new Vector(0,0);
		
		return c;
	}
	
	public Vector dot(Vector a, Vector b){
		
		//TODO Dot Products
		//Default returns empty vector
		
		Vector c = new Vector(0,0);
		
		return c;
	}
	
	public static Vector reduce(Vector a){
		
		//WARNING! DOES NOT WORK WITH Z
		
		Vector r;
		if(a.x == 0 || a.y == 0){
			if(a.x != 0){
				r = new Vector(1,0);
			}
			else if (a.y != 0){
				r = new Vector(0,1);
			}
			else{
				r = new Vector(0,0);
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
