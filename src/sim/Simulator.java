package sim;

import supermarket.ShoppingState;

/**
 * Simulator som kör event i en EventQueue
 *
 */
public class Simulator {

	EventQueue eq;
	ShoppingState state;
	SortedSequence seq;
	
	public Simulator(EventQueue eq, ShoppingState state) {
		this.eq = eq;
		this.state = state;
		this.seq = new SortedSequence(eq);
	}
	
	public void run() {
		
		while(!eq.getArray().isEmpty() && !state.isRunning) {
			eq.nextEvent().execute(seq, state);
		}
	}
	
}
