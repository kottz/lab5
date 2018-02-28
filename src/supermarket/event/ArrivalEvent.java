package supermarket.event;
import sim.Event;
import sim.SimulatorStateADT;
import sim.SortedSequence;
/**
 * Event som körs då en kund anländer.
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
