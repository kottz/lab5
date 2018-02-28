package supermarket.event;
import sim.Event;
import sim.ShoppingState;
import sim.SimulatorStateADT;
import sim.SortedSequence;
import supermarket.customer.CustomerSpawner;
/**
 * Event som körs då simuleringen startar.
 *
 */
public class StartEvent extends Event {
	CustomerSpawner c = new CustomerSpawner();

	@Override
	public void execute(SortedSequence seq, ShoppingState state) {
		
		state.queue.add(c.createCustomer());;	
		seq.sortEventQueue(this);
		state.notifyObservers();
		state.hasChanged();
		
		
	}

}
