package supermarket;

import random.*;
import sim.SimulatorStateADT;
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
	private double lamda;
	private long seed;
	private double Pmin;
	private double Pmax;
	private double Kmin;
	private double Kmax;
	
	private String eventName = "";
	
	private boolean open;
	
	private FIFO queue;
	
	private Customer[] customersShopping;
	private CustomerSpawner factory;
	
	public ShoppingState(double lamda, long seed, int maxCustomers, int numberOfCheckouts, double Pmin, double Pmax, double Kmin, double Kmax) {
		this.maxCustomers = maxCustomers;
		this.lamda = lamda;
		this.seed = seed;
		this.Pmin = Pmin;
		this.Pmax = Pmax;
		this.Kmin = Kmin;
		this.Kmax = Kmax;
		
		customersShopping = new Customer[maxCustomers];
		checkouts = new int[numberOfCheckouts];
		
		ERS = new 
		
		for(int i = 0; i < checkouts.length; i++) {
			checkouts[i] = i+1;
		}
	}
	
	protected int calculateTime() {
		return calculatedTime;
	}
	
	private int calculateArrivalTime() {
		return 1;
	}
	
	private int calculateShoppingTime() {
		return 1;
	}
	
	private int calculateCheckoutTime() {
		return 1;
	}
	
	public void start() {
		eventName = "Start event";
		started = true;
		setChanged();
		notifyObservers();
	}
	
	public void stop() {
		eventName = "Stop event";
		started = false;
		setChanged();
		notifyObservers();
	}
	
	public void leave() {
		eventName = "Leave event";
		setChanged();
		notifyObservers();
	}
	
	public void arrive() {
		eventName = "Arrive event";
		calculatedTime = calculateArrivalTime();
		setChanged();
		notifyObservers();
	}
	
	public void pay(){
		eventName = "Pay event";
		calculatedTime = calculateCheckoutTime();
		setChanged();
		notifyObservers();
	}
	
	public void close(){
		eventName = "Close event";
		setChanged();
		notifyObservers();
	}
	
	public boolean ifStarted(){
		return started;
	}
	
	public boolean ifOpen(){
		return open;
	}
	
	public int getMaxCustomers(){
		return maxCustomers;
	}
	
	public String getEventName(){
		return eventName;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
