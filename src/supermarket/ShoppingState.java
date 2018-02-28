package supermarket;

import java.util.ArrayList;

import random.*;
import sim.SimulatorStateADT;
import supermarket.customer.Customer;
import supermarket.customer.CustomerSpawner;

public class ShoppingState extends SimulatorStateADT {
	
	private ExponentialRandomStream ERS;
	private UniformRandomStream URSPay;
	private UniformRandomStream URSFetch;
	
	public int numberOfCheckouts;
	public int idleCheckouts;
	private int maxCustomers;
	private int completedCheckouts = 0;
	public int missedCustomers = 0;
	private int idleCheckoutsTime = 0;
	private double lamda;
	private long seed;
	private double Pmin;
	private double Pmax;
	private double Kmin;
	private double Kmax;
	
	private String eventName = "";
	
	private boolean open;
	
	public FIFO queue;
	
	public ArrayList<Customer> customersShopping;
	public CustomerSpawner factory;
	
	public ShoppingState(double lamda, long seed, int maxCustomers, int numberOfCheckouts, double Pmin, double Pmax, double Kmin, double Kmax) {
		this.maxCustomers = maxCustomers;
		this.lamda = lamda;
		this.seed = seed;
		this.Pmin = Pmin;
		this.Pmax = Pmax;
		this.Kmin = Kmin;
		this.Kmax = Kmax;
		
		customersShopping = new ArrayList<Customer>();
		this.numberOfCheckouts = numberOfCheckouts;
		this.idleCheckouts = this.numberOfCheckouts;
		
		ERS = new ExponentialRandomStream(lamda, seed);
		URSPay = new UniformRandomStream(Kmin, Kmax, seed);
		URSFetch = new UniformRandomStream(Pmin, Pmax, seed);
	}
	
	private double calculateArrivalTime() {
		return ERS.next();
	}
	
	private double calculateShoppingTime() {
		return URSFetch.next();
	}
	
	private double calculateCheckoutTime() {
		return URSPay.next();
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
	
	public void removeCustomer(Customer c){
		for(Customer cust: customersShopping){
			if(cust.getId() == c.getId()){
				customersShopping.remove(cust);
			}
		}
	}
	
	public void addCustomer(Customer c){
		customersShopping.add(c);
	}
	
	public boolean isStarted(){
		return started;
	}
	
	public boolean isOpen(){
		return open;
	}
	
	public int getMaxCustomers(){
		return maxCustomers;
	}
	
	public String getEventName(){
		return eventName;
	}
	
	public int getNumberOfCustomers(){
		return customersShopping.size();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
