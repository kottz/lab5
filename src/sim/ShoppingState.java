package sim;

import random.*;
import supermarket.FIFO;
import supermarket.customer.Customer;
import supermarket.customer.CustomerSpawner;

public class ShoppingState extends SimulatorStateADT {
	
	private ExponentialRandomStream ERS;
	private UniformRandomStream URS;
	
	private int[] checkouts;
	private int maxCustomers;
	private int completedCheckouts = 0;
	private int missedCustomers = 0;
	private int idleCheckoutsTime = 0;
	//private int arrivalTime = 0;
	//private int shoppingTime = 0;
	//private int checkoutTime = 0;
	
	private boolean open;
	
	private FIFO queue;
	
	private Customer[] customersShopping;
	private CustomerSpawner factory;
	
	public ShoppingState(double lamda, long seed, int maxCustomers, int numberOfCheckouts) {
		this.maxCustomers = maxCustomers;
		checkouts = new int[numberOfCheckouts];
		for(int i = 0; i < checkouts.length; i++) {
			checkouts[i] = i+1;
		}
	}
	
	public void start() {
		started = true;
		setChanged();
		notifyObservers();
	}
	
	public void stop() {
		started = false;
		setChanged();
		notifyObservers();
	}
	
	protected int calculateTime() {
		return calculatedTime;
	}
	
	protected int calculateArrivalTime() {
		return 1;
	}
	
	protected int calculateShoppingTime() {
		return 1;
	}
	
	protected int calculateCheckoutTime() {
		return 1;
	}
	
	public void leave() {
		setChanged();
		notifyObservers();
	}
	
	public void arrive() {
		setChanged();
		notifyObservers();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
