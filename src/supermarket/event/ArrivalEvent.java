package supermarket.event;
import sim.Event;
import supermarket.ShoppingState;
import supermarket.customer.Customer;
import sim.SimulatorStateADT;
import sim.SortedSequence;
/**
 * Event som körs då en kund anländer.
 *
 */
public class ArrivalEvent extends Event {
	
	
	
	public ArrivalEvent(SortedSequence seq,  ShoppingState state) {
		
		time = state.calculateArrivalTime();
		
		
	}

	@Override
	public void execute(SortedSequence seq, ShoppingState state) {
		seq.sortEventQueue(new ArrivalEvent(seq,state));
		state.updateTotalQueueTime(time);
		
		if(state.isOpen() && state.getMaxCustomers() > state.getNumberOfCustomers()) {
			Customer c = state.factory.createCustomer();
			state.queue.add(c);
			seq.sortEventQueue(new PayEvent(seq, state,c, this.time));
		

		state.notifyObservers();
		state.hasChanged();
		}
		else {
			
			state.missedCustomers--;
		}
		
	}

}
