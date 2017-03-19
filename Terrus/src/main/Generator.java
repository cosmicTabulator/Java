package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Generator {

	float a;
	int d;
	
	Random rand;
	
	Vector s;
	Vector f;
	
	
	public Generator(int amplitude, int depth, Vector start, Vector end){
		
		this.a = amplitude;
		this.d = depth;
		
		this.s = start;
		this.f = end;
		
		rand = new Random();
	}
	
	public List<Vector> generate() {
		
		List<Vector> p = new ArrayList<Vector>();
		List<Vector> buffer = new ArrayList<Vector>();
		
		p.add(s);
		p.add(f);
		
		for(int i = 0; i < d; i++){
			for(int n = 0; n < p.size() - 1; n++){
				buffer.add(p.get(n));
				Vector mid = Vector.midpoint(p.get(n), p.get(n+1));
				mid.y = disp(mid.y, a * (1/Math.pow(2, i)));
				buffer.add(mid);
			}
			buffer.add(f);
			p.clear();
			for(Vector q : buffer){
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
