package sim;

/**
 * Simulator som kör event i en EventQueue
 *
 */
public class Simulator {

	EventQueue eventQ;
	
	public Simulator(EventQueue eventQ) {
		this.eventQ = eventQ;
	}
	
	public void simulate() {
		
		while(!eventQ.isEmpty()) {
			eventQ.getfirst().körmig();
			eventQ.removefirst();
		}
	}
	
}
