package sim;

/**
 * Simulator som kör event i en EventQueue
 *
 */
public class Simulator {

	EventQueue eq;
	ettsupermarketState state;
	
	public Simulator(EventQueue eq, ettsupermarketState state) {
		this.eq = eq;
		this.state = state;
	}
	
	public void run() {
		
		while(!eq.isEmpty() && !state.nödbroms) {
			eq.nextEvent().execute(seq, state);
		}
	}
	
}
