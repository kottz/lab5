package sim;

import supermarket.ShoppingState;
import supermarket.ShoppingView;
import supermarket.event.CloseEvent;
import supermarket.event.StartEvent;
import supermarket.event.StopEvent;

public class Optimize {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] missedCustomersAllRuns = new int[999];
		double[] timeCheckoutsHaveBeenIdleAllRuns = new double[999];
		int[] numberOfCashiersAllRuns = new int[999];
		
		int numOfCashiers = 2;
		int maximumCapacity = 5;
		double hoursOpen = 10;
		double stopTime = 999;
		
		double minPickTime = 0.5;
		double maxPickTime = 1.0;
		double minPayTime = 2.0;
		double maxPayTime = 3.0;
		
		double lambda = 1.0;
		long seed = 1234;
		
		for(int i = 0; i < 999; i++){
			numOfCashiers = 2+i;
			
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
			
			missedCustomersAllRuns[i] = state.getNumOfMissedCustomers();
			timeCheckoutsHaveBeenIdleAllRuns[i] = state.getTimeCheckoutsHaveBeenIdle();
		}
		
		double tempMin = Integer.MAX_VALUE;
		
		for(int j = 0; j < timeCheckoutsHaveBeenIdleAllRuns.length; j++){
			if(timeCheckoutsHaveBeenIdleAllRuns[j] < tempMin ){
				tempMin = timeCheckoutsHaveBeenIdleAllRuns[j];
			}
		}
		
		System.out.println("Lägst tid kassor varit lediga: " + tempMin);
	}

}
