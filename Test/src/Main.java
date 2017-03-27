import java.util.HashMap;

public class Main {

	public static void main(String[] args){
		
		HashMap<Pair,String> map = new HashMap<Pair,String>();
		
		Pair key = new Pair(0,0);
		
		map.put(key, "test");
		
		Pair key2 = new Pair(0,0);
		
		System.out.println(map.get(key2));
		
	}
	
}
