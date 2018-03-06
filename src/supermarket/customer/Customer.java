package supermarket.customer;

/**
 *
 * 
 * @author Olof Bourghardt, August Brunnsäter, Oskar Havo , Edward Källstedt
 *
 * Varje enskild kund representeras av instans av denna klass.
 *
 */
public class Customer {

	private int id;
	
	Customer(int id) {
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}
}
