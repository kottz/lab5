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
	private SortedSequence seq;
	private ShoppingState state;
	
	public CloseEvent(SortedSequence seq, ShoppingState state, double time) {
		this.seq = seq;
		this.state = state;
		this.time = time;
		
	}
	public double getTime() {
		return time;
	}
	@Override
	public void execute() {
		//Sätter aktuella värden och uppdaterar.
		state.setCurrentTime(time);
		state.update();

		
		state.closeStore();
	}

}
