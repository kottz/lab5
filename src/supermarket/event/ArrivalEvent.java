package supermarket.event;
import sim.Event;
import supermarket.ShoppingState;
import sim.SimulatorStateADT;
import sim.SortedSequence;
/**
 * Event som körs då en kund anländer.
 *
 */
public class ArrivalEvent extends Event {

	@Override
	public void execute(SortedSequence seq, ShoppingState state) {
		if(state.isOpen() && state.getMaxCustomers() > state.getNumberOfCustomers()) {
		
		seq.sortEventQueue(this);
		state.notifyObservers();
		state.hasChanged();
		}
		else {
			
			
		}
		
	}

}
