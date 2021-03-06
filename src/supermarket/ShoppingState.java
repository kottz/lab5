package supermarket;

import java.util.ArrayList;
import sim.Event;
import supermarket.event.LeaveEvent;
import supermarket.event.StopEvent;

import random.*;
import sim.SimulatorState;
import supermarket.customer.Customer;
import supermarket.customer.CustomerSpawner;

public class ShoppingState extends SimulatorState {
	
	private ExponentialRandomStream ERS;
	private UniformRandomStream URSPay;
	private UniformRandomStream URSFetch;
	
	private double lambda;
	public int numberOfCheckouts;
	public int idleCheckouts;
	public double timeCheckoutsHaveBeenIdle = 0;
	private int maxCustomers;
	private int completedCheckouts = 0;
	public int missedCustomers = 0;
	public double timeOpen;
	private double Pmin;
	private double Pmax;
	private double Kmin;
	private double Kmax;
	private double seed;
	private Event currentEvent;
	private Customer currentCustomer;
	private double lastCheckoutTime;
	
	// Nya
	private double totalQueueTime;
	
	private boolean open;
	
	public FIFO queue;
	
	public ArrayList<Customer> customersShopping;
	public CustomerSpawner factory;
	
	/**
	 * 
	 * @author Olof Bourghardt, August Brunns�ter, Oskar Havo , Edward K�llstedt
	 *
	 */
	
	public ShoppingState(double lambda, long seed, int maxCustomers, int numberOfCheckouts, double Pmin, double Pmax, double Kmin, double Kmax, double timeOpen) {
		this.maxCustomers = maxCustomers;
		this.timeOpen = timeOpen;
		this.lambda = lambda;
		this.Pmin = Pmin;
		this.Pmax = Pmax;
		this.Kmin = Kmin;
		this.Kmax = Kmax;
		this.seed = seed;
		
		customersShopping = new ArrayList<Customer>();
		this.numberOfCheckouts = numberOfCheckouts;
		this.idleCheckouts = this.numberOfCheckouts;
		
		ERS = new ExponentialRandomStream(lambda, seed);
		URSPay = new UniformRandomStream(Kmin, Kmax, seed);
		URSFetch = new UniformRandomStream(Pmin, Pmax, seed);
		this.factory = new CustomerSpawner();
		
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
		customersShopping.remove(c);
		/*for(Customer cust: customersShopping){
			if(cust.getId() == c.getId()){
				customersShopping.remove(cust);
			}
		}*/
	}
	/**
	 * L�gger till en kund i aff�ren
	 * 
	 * @param c kund som skall l�ggas till i aff�ren
	 */
	public void addCustomer(Customer c){
		customersShopping.add(c);
	}
	public boolean freeCheckout() {
		return idleCheckouts > 0;
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
	
	public void setLastCheckoutTime(double t) {
		if(t > lastCheckoutTime) {
			lastCheckoutTime = t;
		}
	}
	public double getLastCheckoutTime() {
		return lastCheckoutTime;
	}
	/**
	 * Returnerar antalet kunder som utf�rt ett k�p i butiken
	 * 
	 * @return
	 */
	public int getCompletedCheckouts(){
		return completedCheckouts;
	}
	/**
	 * Returnerar den totala k�tiden.
	 * @return
	 */
	public double getTotalQueueTime() {
		return totalQueueTime;
	}
	
	/**
	 * Uppdaterar den totala k�tiden med inparametern
	 * @param time
	 */
	public void setTotalQueueTime(double time) {
		totalQueueTime += queue.size()*(time-this.getCurrentTime());
		
	}
	/**
	 * Uppdaterar timeCheckoutsHaveBeenIdle med inparametern.
	 * @param time
	 */
	public void setIdleCheckoutTime(double time) {
		timeCheckoutsHaveBeenIdle += idleCheckouts*(time-this.getCurrentTime());
	}
	/**
	 * Kallar p� setChanged och notifyObservers.
	 */
	public void update() {
		setChanged();
		notifyObservers();
	}
	/**
	 * Kallas fr�n vyn, ger tiden f�r det aktuella eventet
	 * @return
	 */
	public double getCurrentTime() {
		return currentEvent.getTime();
	}
	/**
	 * S�tter Open till true.
	 */
	public void openStore() {
		open = true;
	}
	/**
	 * S�tter Open till false.
	 */
	public void closeStore() {
		open = false;
	}
	
	/**
	 * Getters & Setters
	 * @return
	 */
	public double getLambda() {
		return lambda;
	}
	
	public double getPmin() {
		return Pmin;
	}
	
	public double getPmax() {
		return Pmax;
	}
	
	public double getKmin() {
		return Kmin;
	}
	
	public double getKmax() {
		return Kmax;
	}
	
	public double getSeed() {
		return seed;
	}
	public void setCurrentEvent(Event e) {
		//R�knar ut lastCheckoutTime
	//	if(this.currentEvent instanceof LeaveEvent && !(e instanceof StopEvent)) {
	//		this.lastCheckoutTime = e.getTime();
	//	}
		currentEvent = e;
	}
	public Event getCurrentEvent() {
		return currentEvent;
	}
	public Customer getCurrentCustomer() {
		return currentCustomer;
	}
	public void setCurrentCustomer(Customer c) {
		currentCustomer = c;
	}
	
	public int getNumOfMissedCustomers(){
		return missedCustomers;
	}
	
	public double getTimeCheckoutsHaveBeenIdle(){
		return timeCheckoutsHaveBeenIdle;
	}

}
