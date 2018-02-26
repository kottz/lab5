package sim;

/**
 * Simulator som k�r event i en EventQueue
 *
 */
public class Simulator {

	EventQueue eventQ;
	
	public Simulator(EventQueue eventQ) {
		this.eventQ = eventQ;
	}
	
	public void simulate() {
		
		while(!eventQ.isEmpty()) {
			eventQ.getfirst().k�rmig();
			eventQ.removefirst();
		}
	}
	
}
