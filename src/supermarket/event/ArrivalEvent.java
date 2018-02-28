package supermarket.event;
import sim.Event;
import supermarket.ShoppingState;
import sim.SimulatorStateADT;
import sim.SortedSequence;
/**
 * Event som k�rs d� en kund anl�nder.
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
