
public class Vector {

	int x;
	int y;
	
	public Vector(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int hashCode(){
		
		String s = this.x + "," + this.y;
		
		return s.hashCode();
	}
	
}
