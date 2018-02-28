package sim;

import supermarket.FIFO;
import supermarket.customer.Customer;
import supermarket.customer.CustomerSpawner;

public class ShoppingState extends SimulatorStateADT {
	
	int[] checkouts;
	
	boolean open;
	
	FIFO queue;
	
	Customer[] customersShopping;
	CustomerSpawner factory;
	
	public ShoppingState() {
		
	}
	
	protected void start() {
		started = true;
		setChanged();
		notifyObservers();
	}
	
	protected void stop() {
		started = false;
		setChanged();
		notifyObservers();
	}
	
	protected int calculateTime() {
		return calculatedTime;
	}
	
	protected void leave() {
		setChanged();
		notifyObservers();
	}
	
	protected void arrive() {
		setChanged();
		notifyObservers();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
