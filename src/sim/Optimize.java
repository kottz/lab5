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
		
		System.out.printf("PARAMETRAR\n========\n"
				+ "Antal Kassor, N..........: %s\n"
				+ "Max som ryms, M..........: %s\n"
				+ "Ankomsthastighet, lamba..: %s\n"
				+ "Plocktider, [P_min..Pmax]: [%s..%s]\n"
				+ "Betaltider, [K_min..Kmax]: [%s..%s]\n"
				+ "Frö, f...................: %s\n",
				state.numberOfCheckouts,
				state.getMaxCustomers(),
				state.getLambda(),
				state.getPmin(),state.getPmax(),
				state.getKmin(),state.getKmax(),
				state.getSeed());
		
		System.out.println("\nRESULTAT\n======\n");
		// 1)
		System.out.printf("1) Av %s kunder handlade %s medan %s missades.\n\n",
				state.factory.lastCustomerId(),
				state.factory.lastCustomerId()-state.missedCustomers,
				state.missedCustomers);
		// 2)
		System.out.printf("2) Total tid %s kassor varit lediga: %.2f te.\n",
				state.numberOfCheckouts,
				state.timeCheckoutsHaveBeenIdle);
		// 2b)
		System.out.printf("   Genomsnittlig ledig kassatid: %.2f te (dvs %.2f%% av tiden från öppning tills sista kunden betalat).\n\n",
				state.timeCheckoutsHaveBeenIdle/2,
				(100*state.timeCheckoutsHaveBeenIdle/2)/state.getLastCheckoutTime());
		// 3)
		System.out.printf("3) Total tid %s kunder tvingats köa: %.2f te.\n   Genomsnittlig kötid: %.2f te.",
				state.queue.totalEntries(),
				state.getTotalQueueTime(),
				state.getTotalQueueTime()/state.queue.totalEntries());
		
		
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
