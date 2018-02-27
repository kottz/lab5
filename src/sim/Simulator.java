package sim;

/**
 * Simulator som kör event i en EventQueue
 *
 */
public class Simulator {

	EventQueue eventQ;
	ettsupermarketState state;
	
	public Simulator(EventQueue eventQ, ettsupermarketState state) {
		this.eventQ = eventQ;
		this.state = state;
	}
	
	public void run() {
		
		while(!eventQ.isEmpty() && !state.nödbroms) {
			eventQ.getfirst().körmig();
			eventQ.removefirst();
		}
	}
	
}
