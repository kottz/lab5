package supermarket.customer;

/**
 *
 * 
 * @author Olof Bourghardt, August Brunns�ter, Oskar Havo , Edward K�llstedt
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
