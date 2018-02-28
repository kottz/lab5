package supermarket.event;
import sim.Event;
import sim.SimulatorStateADT;
import sim.SortedSequence;
/**
 * Event som k�rs d� en kund anl�nder.
 *
 */
public class ArrivalEvent extends Event {

	@Override
	public void execute(SortedSequence seq, SimulatorStateADT state) {
		seq.sortEventQueue(this);
		state.notifyObservers();
		state.hasChanged();
		
		
	}

}
