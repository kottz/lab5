package supermarket.event;
import sim.Event;
import sim.SortedSequence;
import supermarket.ShoppingState;
/**
 * Event som körs då snabbköpet stänger för dagen.
 *
 */
public class CloseEvent extends Event {
	
	private double time;
	
	public CloseEvent(double time) {
	
		this.time = time;
		
	}
	@Override
	public void execute(SortedSequence seq, ShoppingState state) {
		
		//state.close();
		
	}

}
