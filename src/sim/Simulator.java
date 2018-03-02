package sim;


/**
 * Simulator som k�r event i en EventQueue
 *
 */
public class Simulator {


	EventQueue eq;
	SortedSequence seq;
	
	public Simulator(EventQueue eq) {
		this.eq = eq;
		this.seq = new SortedSequence(eq);
	}
	
	
	public void run() {
		while(!eq.getArray().isEmpty()) { //Tog bort && isRunning. Simulatorn ska inte vara dependant p� ShoppingState.
			eq.nextEvent().execute();
		}
	}
	
}
