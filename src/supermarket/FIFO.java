package supermarket;

import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.Arrays;

import supermarket.customer.Customer;
import supermarket.customer.CustomerSpawner;

/**
 * FIFO-k� som anv�nds som kassak�.
 *
 */
public class FIFO {

	ArrayList<Customer> fifo = new ArrayList<Customer>();
	
	private int maxSize;
	
	/**
	 * 
	 * @return Storlek p� k�n
	 */
	public int size() {
		return fifo.size();
	}
	
	/**
	 * L�ngsta uppm�tta storleken
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
	 * @return F�rsta kunden
	 */
	public Customer first() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		} else {
			return fifo.get(0);
		}
		
	}
	
	/**
	 * Returnerar en string inneh�llande alla kunder i k�n.
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
	 * L�gger till en kund i k�n.
	 * @param c Customer att l�gga till i k�n.
	 */
	public void add(Customer c) {
		fifo.add(c);
		
		if(fifo.size() > maxSize) {
			maxSize = fifo.size();
		}
	}
	/**
	 * Tar bort f�rsta kunden ur k�n.
	 */
	public void removeFirst() {
		if(this.isEmpty()) {
			throw new NoSuchElementException();
		} else {
			fifo.remove(0);	
		}
		
	}
	
	//F�r att testa..
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
