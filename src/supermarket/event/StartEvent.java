package supermarket.event;
import sim.Event;
import supermarket.ShoppingState;
import sim.SimulatorStateADT;
import sim.SortedSequence;
import supermarket.customer.CustomerSpawner;
/**
 * Event som körs då simuleringen startar.
 *
 */
public class StartEvent extends Event {

	@Override
	public void execute(SortedSequence seq, ShoppingState state) {
		this.time = 0;
		state.queue.add(state.factory.createCustomer());	
		seq.sortEventQueue(this);
		state.notifyObservers();
		state.hasChanged();
		
		
		
	}

}
