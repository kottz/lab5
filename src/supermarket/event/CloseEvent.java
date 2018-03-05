package supermarket.event;
import sim.Event;
import sim.SortedSequence;
import supermarket.ShoppingState;
/**
 * Event som k�rs d� snabbk�pet st�nger f�r dagen.
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
	public String toString() {
		return "St�nger";
	}
	@Override
	public void execute() {
		//S�tter aktuella v�rden och uppdaterar.
		state.timeCheckoutsHaveBeenIdle += state.idleCheckouts*(time-state.getCurrentTime());
		state.setCurrentEvent(this);
		state.update();

		
		state.closeStore();
	}

}
