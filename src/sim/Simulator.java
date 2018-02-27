package sim;

/**
 * Simulator som k�r event i en EventQueue
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
		
		while(!eventQ.isEmpty() && !state.n�dbroms) {
			eventQ.getfirst().k�rmig();
			eventQ.removefirst();
		}
	}
	
}
