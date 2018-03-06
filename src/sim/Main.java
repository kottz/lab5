package sim;
import supermarket.event.*;
import supermarket.ShoppingState;
import supermarket.ShoppingView;

/**
 * 
 * @author Olof Bourghardt, August Brunns�ter, Oskar Havo , Edward K�llstedt
 *
 */
public class Main {

	/**
	 * Mainmetod som kör simulationen
	 *
	 */	
	public static void main(String[] args) {
		
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
		
		//Sim2.txt
	/*	maximumCapacity = 7;
		hoursOpen = 8;
		minPickTime = 0.6;
		maxPickTime = 0.9;
		minPayTime = 0.35;
		maxPayTime = 0.6;
		lambda = 3.0;
		seed = 13;
	*/
		
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
}
