package supermarket;

import java.util.NoSuchElementException;
import java.util.ArrayList;
import supermarket.customer.Customer;


public class FIFO {

	ArrayList<Customer> fifo = new ArrayList<Customer>();
	
	private int maxSize;
	
	
	public int size() {
		return fifo.size();
	}
	
	public int maxSize() {
		return maxSize;
	}
	
	public boolean isEmpty() {
		return fifo.size() == 0;
	}
	
	public Customer first() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		} else {
			return fifo.get(0);
		}
		
	}
	
	
	public String toString() {
		String queueString = "Customers in line: ";
		for(Customer i: fifo) {
			queueString = queueString + "(" + i.getId() + ") ";
		}
		return queueString;
	}
	
	public void add(Customer item) {
		fifo.add(item);
		
		if(fifo.size() > maxSize) {
			maxSize = fifo.size();
		}
	}
	
	public void removeFirst() {
		if(this.isEmpty()) {
			throw new NoSuchElementException();
		} else {
			fifo.remove(0);	
		}
		
	}
}
