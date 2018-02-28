package supermarket.event;
import sim.Event;
import sim.SimulatorStateADT;
import sim.SortedSequence;
import supermarket.FIFO;
/**
 * Event som körs då simuleringen startar.
 *
 */
public class StartEvent extends Event {

	@Override
	public void execute(SortedSequence seq, SimulatorStateADT state) {
		
		
		seq.sortEventQueue(this);
		state.notifyObservers();
		state.hasChanged();
		
		
	}

}
