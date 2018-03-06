package supermarket.customer;

/**
 * 
 *
 * 
 * @author Olof Bourghardt, August Brunns�ter, Oskar Havo , Edward K�llstedt
 *
 * Anv�nds f�r att skapa kunder med olika id:n. 
 *
 */
public class CustomerSpawner {

	private int counter;
	
	public CustomerSpawner() {
		counter = 0;
	}
	
	public Customer createCustomer() {
		Customer kund = new Customer(counter);
		counter++;
		return kund;
	}
	public int lastCustomerId() {
		return counter-1;
	}
}
