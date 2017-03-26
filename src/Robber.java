import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;



public class Robber {

    /*
     * This method should return true if the robber can rob all the houses in the neighborhood,
     * which are represented as a graph, and false if he cannot. The function should also print to the console the
     * order in which the robber should rob the houses if he can rob the houses. You do not need to print anything
     * if all the houses cannot be robbed.
     */
    public boolean canRobAllHouses(Graph neighborhood) {
        /*
         * do bfs from every house until every house can be reached
         * add requirement that you cannot process a node unless it is unlocked
         * every time you look at a nodes edge, add a keyAquired to the house the key unlocks
         */
    	LinkedList<String> q;
    	ArrayList<String> order;
    	for(String house : neighborhood.getHouses()){
    		order = new ArrayList<String>();
    		q = new LinkedList<String>();
    		q.add(house);
    		/*
    		 * run bfs
    		 */
    		while(!q.isEmpty()){
    			String temp = q.remove();
    			if(!neighborhood.isLocked(temp)){
    				order.add(temp);
    				for(String s : neighborhood.getKeys(temp)){
    					neighborhood.getHouse(s).plusKeyAquired();
    					q.add(s);
    				}
    			}
    		}
    		if(order.size() == neighborhood.getHouses().size()){
    			System.out.println(order.toString().substring(1, (order.toString().length() - 1)));
    			return true;
    		}
    	}
        return false;
    }
    
    
    
    /*
     *
     */
    public void maximizeLoot(String lootList) {
        /*
         * open the file first
         */
    	try {
    		File file = new File(lootList);
			Scanner scan = new Scanner(file);
			LootBag lootBag = new LootBag(scan);
			lootBag.pickLoot();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	

    }


    public void scheduleMeetings(String buyerList) {
        //TODO: Implement Function

    }
}
