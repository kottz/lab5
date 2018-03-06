package supermarket.customer;

/**
 * 
 *
 * 
 * @author Olof Bourghardt, August Brunnsäter, Oskar Havo , Edward Källstedt
 *
 * Används för att skapa kunder med olika id:n. 
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
	 * returnerar föregående kund id.
	 * @return
	 */
	public int lastCustomerId() {
		return counter-1;
	}
}
