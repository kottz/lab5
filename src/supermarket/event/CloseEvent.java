package supermarket.event;
import sim.Event;
import sim.SortedSequence;
import supermarket.ShoppingState;
/**
 * 
 * 
 * @author Olof Bourghardt, August Brunnsäter, Oskar Havo , Edward Källstedt
 *
 * Event som körs då snabbköpet stänger för dagen.
 *
 */
public class CloseEvent extends Event {
	
	private ShoppingState state;
	
	/**
	 * 
	 * @param seq
	 * @param state
	 * @param time
	 */
	public CloseEvent(SortedSequence seq, ShoppingState state, double time) {
		this.seq = seq;
		this.state = state;
		this.time = time;
		
	}

	
	public String toString() {
		return "Stänger";
	}
	@Override
	public void execute() {
		//Sätter aktuella värden och uppdaterar.
		state.setIdleCheckoutTime(time);
		state.setTotalQueueTime(time);
		state.setCurrentEvent(this);
		state.update();

		
		state.closeStore();
	}

}
