package sim;

import supermarket.ShoppingState;
import supermarket.ShoppingView;
import supermarket.event.CloseEvent;
import supermarket.event.StartEvent;
import supermarket.event.StopEvent;
import supermarket.OptimizeView;

public class Optimize implements K {

	int[] missedCustomersAllRuns = new int[530];
	double[] timeCheckoutsHaveBeenIdleAllRuns = new double[530];
	int[] numberOfCashiersAllRuns = new int[530];
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
		
		for(int i = 0; i < 530; i++){
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
		OptimizeView view = new OptimizeView(state);
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
		int minCashiers = Integer.MAX_VALUE;
		int minMissedCustomers = Integer.MAX_VALUE;
		for(int i = 0; i < missedCustomersAllRuns.length; i++){
			if(missedCustomersAllRuns[i] < minMissedCustomers){
				minMissedCustomers = missedCustomersAllRuns[i];
				minCashiers = numberOfCashiersAllRuns[i];
			}
		}
		return minCashiers;
	}
	
}
