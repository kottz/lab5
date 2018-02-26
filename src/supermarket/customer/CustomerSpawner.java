package supermarket.customer;

/**
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
}
