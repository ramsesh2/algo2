
public class House {
	/*
	 * Represents a house node for the graph to use
	 */
	
	private boolean locked;	
	private String name;
	private int keysRequired;
	private int keysAquired;
	
	public House(String n, boolean l){
		locked = l;
		name = n;
		keysRequired = 0;
		keysAquired = 0;
	}
	
	public void plusKeyReq(){
		keysRequired++;
	}
	
	public void plusKeyAquired(){
		if(getLocked()){
			keysAquired++;
			if(keysAquired >= keysRequired){
				locked = false;
			}
		}
	}
	
	public String getName(){
		return name;
	}
	
	public boolean getLocked(){
		return locked;
	}
}
