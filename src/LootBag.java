import java.util.HashSet;
import java.util.Scanner;

/*
 * class will hold ingrediants data and will produce the maximum loot choice
 */
public class LootBag {
	
	
	/*
	 * this class represents a single piece of loot
	 */
	class Loot{
		private String name;
		private double quantity;
		private double price;
		
		public Loot(String n, double q, double p){
			name = n;
			quantity = q;
			price = p;
		}
		
		/*
		 * this constructor returns a new loot which is a copy of another loot but with the specified quantity.
		 * this is the loot that fruitcake takes 
		 */
		public Loot(Loot stock, double ammount){
			name = stock.getName();
			quantity = ammount;
			price = stock.getPrice();
			stock.take(ammount);
		}
		
		public boolean stocked(){
			return quantity > 0;
		}
		
		public void take(double q){
			quantity -= q;
		}
		
		public String getName(){
			return name;
		}
		
		public double getQuantity(){
			return quantity;
		}
		
		public double getPrice(){
			return price;
		}
	}
	
	private HashSet<Loot> bag;
	private double cap;
	
	public LootBag(Scanner scan){
		bag = new HashSet<Loot>();
		cap = scan.nextDouble();
		
		/*
		 * add each ingrediant 
		 */
		Scanner lScan;
		while(scan.hasNext()){
			lScan = new Scanner(scan.nextLine());
			lScan.useDelimiter("[\\s+,\n\r]");
			if(lScan.hasNext()){
				String name = lScan.next();
				String quantity = lScan.next();
				String price = lScan.nextLine();
//				double q = Double.parseDouble(quantity);
				System.out.println(quantity);
//				double p = Double.parseDouble(price);
				//bag.add(new Loot(name, quantity, price));
			}
		}
	}
	
	private Loot bestInBag(){
		Loot best = null;
		for(Loot l : bag){
			if(l.getQuantity() != 0){
				if(best == null){
					best = l;
				}
				else if(best.getPrice() < l.getPrice()) best = l;
			}
		}
		return best;
	}
	
	/*
	 * greedy algorithm,
	 * pick the item with highest price,
	 * take as much as you can of that
	 * recursively call again choosing next most valuable item
	 */
	private void greedyPick(HashSet<Loot> fill, double capacity){
		if(capacity == 0){
			return;
		}
		else{
			Loot best = bestInBag();
			if(best == null){
				return;
			}
			else if(best.getQuantity()<= capacity){
				/*
				 * more space in bag than amount of best item left
				 * take all of the item and add it to the fill bag
				 */
				double amt = best.getQuantity();
				fill.add(new Loot(best, amt));
				greedyPick(fill, capacity-amt);
			}
			else{
				/*
				 * capacity < amount in stock
				 * more of best stock item then room left in bag
				 * take as much of best and return
				 */
				fill.add(new Loot(best, capacity));
				return;
			}
		}
	}
	
	public void pickLoot(){
		if(bag != null){
			HashSet<Loot> takeBag = new HashSet<Loot>();
			greedyPick(takeBag, cap);
			for(Loot l : takeBag){
				System.out.println(l.getName() + ", " + l.getQuantity());
			}
		}
	}
	
	
}
