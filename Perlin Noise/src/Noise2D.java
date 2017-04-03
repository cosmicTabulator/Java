import java.util.HashMap;
import java.util.Random;

public class Noise2D {

	Random rand;
	int size;
	long seed;
	HashMap<Integer,Vector> map = new HashMap<Integer,Vector>();
	
	public Noise2D(int size){
		
		this.size = size;
		
		rand = new Random();
		
		for(int x = 0; x < size +1; x++){
			for(int y = 0; y < size +1; y++){
				Vector v = new Vector(x,y);
				double theta = rand.nextDouble() * Math.PI * 2;
				double xVal = Math.sin(theta);
				double yVal = Math.cos(theta);
				Vector g = new Vector(xVal, yVal);
				map.put(v.hashCode(), g);
				
			}
		}
		
	}
	
	public double getVal(double x, double y){
		
		double valf;
		
		double i = Math.floor(x);
		double j = Math.floor(y);
		
		Vector pos = new Vector(x - i,y - j);
		
		Vector nw = map.get(new Vector(i, j).hashCode());
		Vector ne = map.get(new Vector(i+1, j).hashCode());
		Vector sw = map.get(new Vector(i, j+1).hashCode());
		Vector se = map.get(new Vector(i+1, j+1).hashCode());
		
		Vector nwP = pos;
		Vector neP = new Vector(pos.x-1, pos.y);
		Vector swP = new Vector(pos.x, pos.y-1);
		Vector seP = new Vector(pos.x-1, pos.y-1);
		
		double val1 = Vector.dot(nw, nwP);
		double val2 = Vector.dot(ne, neP);
		double val3 = Vector.dot(sw, swP);
		double val4 = Vector.dot(se, seP);
		
		double val01 = val1*blend(1-pos.x) + val2*blend(pos.x);
		double val02 = val3*blend(1-pos.x) + val4*blend(pos.x);
		
		valf = val01*blend(1-pos.y) + val02*blend(pos.y);
		
		return valf;
	}
	
	public double blend(double t){
		return 6*t*t*t*t*t - 15*t*t*t*t + 10*t*t*t;
	}
	
}
