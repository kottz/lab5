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
	
	public StartEvent() {
		
		this.time=0.00d;
		
	}

	@Override
	public void execute(SortedSequence seq, ShoppingState state) {
	
		state.queue.add(state.factory.createCustomer());	
		seq.sortEventQueue(new ArrivalEvent(seq,state));
		state.notifyObservers();
		state.hasChanged();
		
		
		
	}

}
