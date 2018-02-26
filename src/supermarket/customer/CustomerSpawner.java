package supermarket.customer;

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
