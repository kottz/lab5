package supermarket;

import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.Arrays;

import supermarket.customer.Customer;
import supermarket.customer.CustomerSpawner;

/**
 * FIFO-kö som används som kassakö.
 *
 */
public class FIFO {

	ArrayList<Customer> fifo = new ArrayList<Customer>();
	
	private int maxSize;
	
	/**
	 * 
	 * @return Storlek på kön
	 */
	public int size() {
		return fifo.size();
	}
	
	/**
	 * Längsta uppmätta storleken
	 * @return maxSize
	 */
	public int maxSize() {
		return maxSize;
	}
	
	/**
	 * Returnerar true om 
	 * @return true if empty
	 */
	public boolean isEmpty() {
		return fifo.size() == 0;
	}
	
	/**
	 * 
	 * @return Första kunden
	 */
	public Customer first() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		} else {
			return fifo.get(0);
		}
		
	}
	
	/**
	 * Returnerar en string innehållande alla kunder i kön.
	 */
	public String toString() {
		String queueString = "[";
		for(Customer i: fifo) {
			queueString = queueString + i.getId() + ", ";
		}
		queueString = queueString.substring(0, queueString.length()-2) +"]";
		return queueString;
	}
	
	/**
	 * Lägger till en kund i kön.
	 * @param c Customer att lägga till i kön.
	 */
	public void add(Customer c) {
		fifo.add(c);
		
		if(fifo.size() > maxSize) {
			maxSize = fifo.size();
		}
	}
	/**
	 * Tar bort första kunden ur kön.
	 */
	public void removeFirst() {
		if(this.isEmpty()) {
			throw new NoSuchElementException();
		} else {
			fifo.remove(0);	
		}
		
	}
	
	//För att testa..
	/*public static void main(String[] args) {
		CustomerSpawner spawn = new CustomerSpawner();
		FIFO test1 = new FIFO();
		for(int i = 0; i<5; i++) {
			test1.add(spawn.createCustomer());
		}
		System.out.println(test1.toString());
		System.out.println(test1.first().getId());
		test1.removeFirst();
		System.out.println(test1.first().getId());
	}*/
}
