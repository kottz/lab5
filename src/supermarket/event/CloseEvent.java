package supermarket.event;
import sim.Event;
import sim.SortedSequence;
import supermarket.ShoppingState;
/**
 * 
 * 
 * @author Olof Bourghardt, August Brunns�ter, Oskar Havo , Edward K�llstedt
 *
 * Event som k�rs d� snabbk�pet st�nger f�r dagen.
 *
 */
public class CloseEvent extends Event {
	
	private double time;
	private SortedSequence seq;
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
	public double getTime() {
		return time;
	}
	public String toString() {
		return "St�nger";
	}
	@Override
	public void execute() {
		//S�tter aktuella v�rden och uppdaterar.
		state.setIdleCheckoutTime(time);
		state.setTotalQueueTime(time);
		state.setCurrentEvent(this);
		state.update();

		
		state.closeStore();
	}

}
