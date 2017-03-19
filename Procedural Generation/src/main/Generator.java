package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Generator {

	float a;
	int d;
	
	Random rand;
	
	Point s;
	Point f;
	
	
	public Generator(int amplitude, int depth, Point start, Point end){
		
		this.a = amplitude;
		this.d = depth;
		
		this.s = start;
		this.f = end;
		
		rand = new Random();
	}
	
	public List<Point> generate() {
		
		List<Point> p = new ArrayList<Point>();
		List<Point> buffer = new ArrayList<Point>();
		
		p.add(s);
		p.add(f);
		
		for(int i = 0; i < d; i++){
			for(int n = 0; n < p.size() - 1; n++){
				buffer.add(p.get(n));
				Point mid = Point.midpoint(p.get(n), p.get(n+1));
				mid.y = disp(mid.y, a * (1/Math.pow(2, i)));
				buffer.add(mid);
			}
			buffer.add(f);
			p.clear();
			for(Point q : buffer){
				p.add(q);
			}
			buffer.clear();
		}

		return p;
		
	}
	
	double disp(double v, double dis){
		
		double value = rand.nextDouble() * dis * 2;
		
		value -= dis;
		
		v += value;
		
		return v;
	}
	
}
