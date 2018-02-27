package sim;

/**
 * Simulator som k�r event i en EventQueue
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
		
		while(!eq.isEmpty() && !state.n�dbroms) {
			eq.nextEvent().execute(seq, state);
		}
	}
	
}
