

import java.util.ArrayList;
import java.util.List;

public class Point {

	float x;
	float y;
	
	public Point(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	public static Point midpoint(Point a, Point b){
		
		Point out = new Point(0,0);
		
		out.x = ( a.x + b.x )/2;
		out.y = ( a.y + b.y )/2;
		
		return out;
	}

	public static List<Point> interpolate(List<Point> l){
		
		long size = Math.round(l.get(l.size()-1).x);
		
		List<Point> out = new ArrayList<Point>();
		
		for(int i = (int) Math.round(l.get(0).x); i < (int) (Math.round(l.get(0).x) + 800); i++){
			
			Point pre = searchForPre(l, i);
			Point post = searchForPost(l, i);
			
			
			double m = (post.y - pre.y)/(post.x - pre.x);
			double b = pre.y - (pre.x * m);
			
			out.add(new Point(i, (float) (b + i*m)));
		}
		
		return out;
	}
	
	private static Point searchForPre(List<Point> l, int i) {
		
		Point closest = new Point(0,0);
		double dist = Double.MAX_VALUE;
		
		for(Point p : l){
			
			if(Math.abs(p.x-i) < dist && p.x <= i){
				closest = p;
				dist = Math.abs(p.x-i);
			}
			
		}
		
		return closest;
	}
	
	private static Point searchForPost(List<Point> l, int i) {
		
		Point closest = new Point(0,0);
		double dist = Double.MAX_VALUE;
		
		for(Point p : l){
			
			if(Math.abs(p.x-i) < dist && p.x > i){
				closest = p;
				dist = Math.abs(p.x-i);
			}
			
		}
		
		return closest;
	}

	public static Point searchFor(List<Point> l, int v){
		
		Point closest = new Point(0,0);
		double dist = Double.MAX_VALUE;
		
		for(Point p : l){
			
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
	
}
