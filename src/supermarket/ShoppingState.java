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
	public int timeCheckoutsHaveBeenIdle = 0;
	private int maxCustomers;
	private int completedCheckouts = 0;
	public int missedCustomers = 0;
	public double timeOpen;
	
	// Nya
	private double totalQueueTime;
	
	private boolean open;
	
	public FIFO queue;
	
	public ArrayList<Customer> customersShopping;
	public CustomerSpawner factory;
	
	public ShoppingState(double lamda, long seed, int maxCustomers, int numberOfCheckouts, double Pmin, double Pmax, double Kmin, double Kmax, double timeOpen) {
		this.maxCustomers = maxCustomers;
		this.timeOpen = timeOpen;
		
		customersShopping = new ArrayList<Customer>();
		this.numberOfCheckouts = numberOfCheckouts;
		this.idleCheckouts = this.numberOfCheckouts;
		
		ERS = new ExponentialRandomStream(lamda, seed);
		URSPay = new UniformRandomStream(Kmin, Kmax, seed);
		URSFetch = new UniformRandomStream(Pmin, Pmax, seed);
		
		queue = new FIFO();
	}
	/**
	 * Returnerar hur l�ng tid det tog f�r n�sta kund att komma till aff�ren
	 * 
	 * @return
	 */
	public double calculateArrivalTime() {
		return ERS.next();
	}
	/**
	 * Returnerar hur l�mg tid det tog f�r en kund att plocka ihop sina varor
	 * 
	 * @return
	 */
	public double calculateShoppingTime() {
		return URSFetch.next();
	}
	/**
	 * Returnerar hur l�ng tid det tog f�r en kund att betala f�r sina varor
	 * 
	 * @return
	 */
	public double calculateCheckoutTime() {
		return URSPay.next();
	}
	
	/**
	 * G�r s� att tillst�ndet �r startat
	 */
	public void start() {
		isRunning = true;
	}
	/**
	 * G�r s� att tillst�ndet �r stannar
	 */
	public void stop() {
		isRunning = false;
	}
	/**
	 * Tar bort en kund ur aff�ren
	 * 
	 * @param c kund som skall tas bort
	 */
	public void removeCustomer(Customer c){
		for(Customer cust: customersShopping){
			if(cust.getId() == c.getId()){
				customersShopping.remove(cust);
			}
		}
	}
	/**
	 * L�gger till en kund i aff�ren
	 * 
	 * @param c kund som skall l�ggas till i aff�ren
	 */
	public void addCustomer(Customer c){
		customersShopping.add(c);
	}
	/**
	 * Returnerar om tillst�ndet �r startat eller ej
	 * 
	 * @return
	 */
	public boolean isRunning(){
		return isRunning;
	}
	/**
	 * Returnerar om butiken �r �ppen eller st�ngd
	 * 
	 * @return
	 */
	public boolean isOpen(){
		return open;
	}
	/**
	 * Returnerar antalet kunder som maximalt f�r vistas i butiken samtidigt
	 * 
	 * @return
	 */
	public int getMaxCustomers(){
		return maxCustomers;
	}
	/**
	 * Returnerar antalet kunder som �r i butiken vid tidpunkten d� metoden anroppas
	 * 
	 * @return
	 */
	public int getNumberOfCustomers(){
		return customersShopping.size();
	}
	/**
	 * �kar antalet kunder som utf�rt ett k�p i butiken
	 * 
	 */
	public void completedCheckout(){
		completedCheckouts++;
	}
	/**
	 * Returnerar antalet kunder som utf�rt ett k�p i butiken
	 * 
	 * @return
	 */
	public int getCompletedCheckouts(){
		return completedCheckouts;
	}
	
	public double getTotalQueueTime() {
		return totalQueueTime;
	}
	
	public double updateTotalQueueTime(double updateTime) {
		return (totalQueueTime+=updateTime);
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
