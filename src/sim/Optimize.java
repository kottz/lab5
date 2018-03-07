package sim;

import supermarket.ShoppingState;
import supermarket.ShoppingView;
import supermarket.event.CloseEvent;
import supermarket.event.StartEvent;
import supermarket.event.StopEvent;

public class Optimize implements K {

	int[] missedCustomersAllRuns = new int[999];
	double[] timeCheckoutsHaveBeenIdleAllRuns = new double[999];
	int[] numberOfCashiersAllRuns = new int[999];
	double minCheckoutsIdle;
	int minCashiers;
	
	int numOfCashiers = 2;
	int maximumCapacity = 7;
	double hoursOpen = 8;
	double stopTime = 999;
	
	double minPickTime = 0.6;
	double maxPickTime = 0.9;
	double minPayTime = 0.35;
	double maxPayTime = 0.6;
	
	double lambda = 3.0;
	long seed = 13;


	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Optimize o = new Optimize();
		o.run();
	}
	
	public void run(){
		maximumCapacity = M;
		lambda = L;
		
		minPickTime = LOW_COLLECTION_TIME;
		maxPickTime = HIGH_COLLECTION_TIME;
		
		minPayTime = LOW_PAYMENT_TIME;
		maxPayTime = HIGH_PAYMENT_TIME;
		
		seed = SEED;
		hoursOpen = END_TIME;
		stopTime = STOP_TIME;
		
		
		
		for(int i = 0; i < 999; i++){
			numOfCashiers = 2+i;
			
			ShoppingState state = new ShoppingState(lambda, seed, maximumCapacity, numOfCashiers,
					minPickTime, maxPickTime, minPayTime, maxPayTime, hoursOpen);
			//ShoppingView view = new ShoppingView(state);
			//state.addObserver(view);
			EventQueue eq = new EventQueue();
			
			Simulator sim1 = new Simulator(eq,state);
			SortedSequence seq = sim1.seq;
			
			Event start = new StartEvent(seq,state);
			Event close = new CloseEvent(seq,state,hoursOpen);
			Event stop = new StopEvent(seq,state,stopTime);
			eq.addEvent(start);
			eq.addEvent(close);
			eq.addEvent(stop);
			
			sim1.run();
			
			missedCustomersAllRuns[i] = state.getNumOfMissedCustomers();
			timeCheckoutsHaveBeenIdleAllRuns[i] = state.getTimeCheckoutsHaveBeenIdle();
			numberOfCashiersAllRuns[i] = numOfCashiers;
		}
		
		minCashiers = getMinCashiers();
		
		numOfCashiers = minCashiers;
		
		ShoppingState state = new ShoppingState(lambda, seed, maximumCapacity, numOfCashiers,
				minPickTime, maxPickTime, minPayTime, maxPayTime, hoursOpen);
		ShoppingView view = new ShoppingView(state);
		state.addObserver(view);
		EventQueue eq = new EventQueue();
		
		Simulator sim1 = new Simulator(eq,state);
		SortedSequence seq = sim1.seq;
		
		Event start = new StartEvent(seq,state);
		Event close = new CloseEvent(seq,state,hoursOpen);
		Event stop = new StopEvent(seq,state,stopTime);
		eq.addEvent(start);
		eq.addEvent(close);
		eq.addEvent(stop);
		
		sim1.run();
		
	}
	
	private int getMinCashiers(){
		double minTime = Double.MAX_VALUE;
		int minMissedCustomers = -1;
		for(int i = 0; i < missedCustomersAllRuns.length; i++){
			if(missedCustomersAllRuns[i] == 0 && timeCheckoutsHaveBeenIdleAllRuns[i] < minTime){
				minTime = timeCheckoutsHaveBeenIdleAllRuns[i];
				minMissedCustomers = missedCustomersAllRuns[i];
			}
		}
		return getCashier(minMissedCustomers, minTime);
	}
	
	private int getCashier(int missed, double time){
		for(int i = 0; i < missedCustomersAllRuns.length; i++){
			if(missed == missedCustomersAllRuns[i] && time == timeCheckoutsHaveBeenIdleAllRuns[i]){
				return numberOfCashiersAllRuns[i];
			}
		}
		return -1;
	}
}
