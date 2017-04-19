import java.util.ArrayList;

public class Matrix {
	
	ArrayList<Double> m = new ArrayList<Double>();
	
	double a[] = new double[9];
	
	public Matrix(double array[]){
		
		this.set(array);
		
	}
	
	
	public int set(double array[]){
		
		if(array.length != 9){
			return -1;
		}
		
		m.clear();
		
		for(double e : array){
			
			m.add(e);
			
		}
		
		a = array;
		
		return 0;
		
	}
	
	public Vector3 mult(Vector3 v){
		
		Vector3 x = new Vector3(a[0], a[3], a[6]);
		Vector3 y = new Vector3(a[1], a[4], a[7]);
		Vector3 z = new Vector3(a[2], a[5], a[8]);
		
		x = Vector3.mult(x, v.x);
		y = Vector3.mult(y, v.y);
		z = Vector3.mult(z, v.z);
		
		Vector3 o = Vector3.add(x,Vector3.add(y, z));
		
		return o;
		
	}
	
	
}
