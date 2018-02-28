package supermarket.event;
import sim.Event;
import sim.ShoppingState;
import sim.SimulatorStateADT;
import sim.SortedSequence;
/**
 * Event som k�rs d� simuleringen avslutas.
 *
 */
public class StopEvent extends Event {

	@Override
	public void execute(SortedSequence seq, ShoppingState state) {
		seq.sortEventQueue(this);
		state.notifyObservers();
		state.hasChanged();
		
		
	}

}
