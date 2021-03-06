package supermarket.event;
import sim.Event;
import sim.SimStopEvent;
import supermarket.ShoppingState;
import sim.SimulatorState;
import sim.SortedSequence;
/**
 * 
 *
 * 
 * @author Olof Bourghardt, August Brunns�ter, Oskar Havo , Edward K�llstedt
 *
 * Event som k�rs d� simuleringen avslutas.
 *
 */
public class StopEvent extends SimStopEvent {
	
	private ShoppingState state;
	
	/**
	 * 
	 * @param seq
	 * @param state
	 * @param time
	 */
	public StopEvent(SortedSequence seq, ShoppingState state, double time) {
		this.seq = seq;
		this.state = state;
		this.time = time;
	}

	public String toString() {
		return "Stop";
	}
	@Override
	public void execute() {
		//S�tter aktuella v�rden och uppdaterar.
		state.setCurrentEvent(this);
		

		state.stop();
		state.update();
		
	}

}
