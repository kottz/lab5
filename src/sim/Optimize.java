package sim;

import java.util.ArrayList;
import java.util.Random;

import supermarket.ShoppingState;
import supermarket.ShoppingView;
import supermarket.event.CloseEvent;
import supermarket.event.StartEvent;
import supermarket.event.StopEvent;
import supermarket.OptimizeView;

public class Optimize implements K {

	int[] missedCustomersAllRuns = new int[M];
	double[] timeCheckoutsHaveBeenIdleAllRuns = new double[M];
	int[] numberOfCashiersAllRuns = new int[M];
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
		System.out.println("Final result: " + o.metod3(SEED));
	}
	
	public int run(long seed){
		maximumCapacity = M;
		lambda = L;
		
		minPickTime = LOW_COLLECTION_TIME;
		maxPickTime = HIGH_COLLECTION_TIME;
		
		minPayTime = LOW_PAYMENT_TIME;
		maxPayTime = HIGH_PAYMENT_TIME;
		
		this.seed = seed;
		hoursOpen = END_TIME;
		stopTime = STOP_TIME;
		
		int minMissedCustomers = Integer.MAX_VALUE;
		
		for(int i = 0; i < M; i++){
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
			
			if(state.getNumOfMissedCustomers() < minMissedCustomers) {
				minMissedCustomers = state.getNumOfMissedCustomers();
				minCashiers = numOfCashiers;
			}
		}
		
		numOfCashiers = minCashiers;
		return numOfCashiers;
		
		/*ShoppingState state = new ShoppingState(lambda, seed, maximumCapacity, numOfCashiers,
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
		
		sim1.run();*/
		
	}
	
	public int metod3(long seed){
		boolean keepGoing = true;
		int counter = 0;
		int biggestValue = Integer.MIN_VALUE;
		ArrayList<Integer> optimalCashiers = new ArrayList<Integer>();
		Random random = new Random(seed);
		while(keepGoing){
			optimalCashiers.add(run(random.nextLong()));
			if(optimalCashiers.size() > 1){
				if(optimalCashiers.get(optimalCashiers.size()-1) >= biggestValue){
					biggestValue = optimalCashiers.get(optimalCashiers.size()-1);
					counter =0;
					
					//System.out.println(counter);
				}else{
					counter++;
					if(counter == 100){
						return optimalCashiers.get(optimalCashiers.size()-1);
					}
				}
			}
		}
		return -1;
	}
	
}
