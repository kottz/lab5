package supermarket.event;
import sim.Event;
import sim.SortedSequence;
import supermarket.ShoppingState;
/**
 * Event som körs då snabbköpet stänger för dagen.
 *
 */
public class CloseEvent extends Event {

	public CloseEvent() {
	
		//time = 0.0d;
		
	}
	@Override
	public void execute(SortedSequence seq, ShoppingState state) {
		
		//state.close();
		
	}

}
