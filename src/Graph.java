import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 */
public class Graph {

    /*
     * Creates a graph to represent the neighborhood, where unlocked is the file name for the unlocked houses
     * and keys is the file name for which houses have which keys.
     */
	private HashMap<String, HashSet<String>> g;
	private HashMap<String, House> houses;
	
    public Graph(String unlocked, String keys) {
    	g = new HashMap<String, HashSet<String>>();
    	houses = new HashMap<String, House>();
    	
    	try {
    		/*
    		 * Grab each house in unlocked, put it in as a new key that is an unlocked house, with an empty set of neighbor keys
    		 */
        	File file = new File(unlocked);
			Scanner scan = new Scanner(file);
	    	while(scan.hasNext() && scan.next().equals("House")){
	    		String name = "House " + scan.next();
	    		/*
	    		 * stores house data in both g and houses map
	    		 */
	    		g.put(name, new HashSet<String>());
	    		houses.put(name, new House(name, false));
	    	}
	    	
	    	/*
	    	 * open the keys file, if the house exists in the map, add its keys to its hashset,
	    	 * otherwise add a new house to the map with an empty neighbor key set and populate the set
	    	 */
	    	file = new File(keys);
	    	scan = new Scanner(file);
	    	while(scan.hasNext()){
	    		String line = scan.nextLine();
	    		Scanner lScan = new Scanner(line);
	    		lScan.useDelimiter(":");
	    		String node = lScan.next();
	    		if(!containsVertex(node)){
	    			/*
		    		 * stores house data in both g and houses map
		    		 */
	    			g.put(node, new HashSet<String>());
	    			houses.put(node, new House(node, true));
	    		}
	    		/*
	    		 * skip the colon
	    		 */
	    		lScan.useDelimiter(" ");
	    		lScan.next();
	    		lScan.useDelimiter("[,\r\n]");
	    		String houseKey;
	    		while(lScan.hasNext()){
	    			houseKey = lScan.next().substring(1);
		    		if(!containsVertex(houseKey)){
		    			/*
			    		 * stores house data in both g and houses map
			    		 */
		    			g.put(houseKey, new HashSet<String>());
		    			houses.put(houseKey, new House(houseKey, true));
		    		}
	    			g.get(node).add(houseKey);
	    			houses.get(houseKey).plusKeyReq();
	    		}	
	    	}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    	
    	
    }
    
    /*
     * This method returns a set of strings containing all house names
     */
    public Set<String> getHouses(){
    	return houses.keySet();
    }
    
    public Set<String> getKeys(String house){
    	return g.get(house);
    }
    
    public House getHouse(String name){
    	return houses.get(name);
    }

    /*
     * This method should return true if the Graph contains the vertex described by the input String.
     */
    public boolean containsVertex(String node) {
        if(g != null){
        	/*
        	 * any house in the graph should be in the houses map
        	 */
        	for(String name : houses.keySet()){
        		if(name.equals(node)) return true;
        	}
        }
        return false;
    }

    /*
     * This method should return true if there is a direct edge from the vertex
     * represented by start String and end String.
     */
    public boolean containsEdge(String start, String end) {
        if(g.containsKey(start)){
        	if(g.get(start).contains(end)) return true;
        }
        return false;
    }

    /*
     * This method returns true if the house represented by the input String is locked
     * and false is the house has been left unlocked.
     */
    public boolean isLocked(String house) {
    	if(houses.containsKey(house)) return houses.get(house).getLocked();
    	return false;
    }
}
