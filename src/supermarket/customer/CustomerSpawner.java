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
	/**
	 * Skapar en ny kund.
	 * @return
	 */
	public Customer createCustomer() {
		Customer kund = new Customer(counter);
		counter++;
		return kund;
	}
	/**
	 * returnerar f�reg�ende kund id.
	 * @return
	 */
	public int lastCustomerId() {
		return counter-1;
	}
}
