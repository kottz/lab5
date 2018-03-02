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
	
	private double time;
	
	public ArrivalEvent(SortedSequence seq,  ShoppingState state, double time) {
		this.time = time;
	}

	@Override
	public void execute(SortedSequence seq, ShoppingState state) {
		double nextArrivalTime = time + state.calculateArrivalTime();
		seq.sortEventQueue(new ArrivalEvent(seq,state,nextArrivalTime));
		state.updateTotalQueueTime(time);
		
		if(state.isOpen() && state.getMaxCustomers() > state.getNumberOfCustomers()) {
			Customer c = state.factory.createCustomer();
			state.queue.add(c);
			double nextPayTime = time + state.calculateShoppingTime();
			seq.sortEventQueue(new PayEvent(seq, state,c, nextPayTime));
		

		state.notifyObservers();
		state.hasChanged();
		}
		else {
			
			state.missedCustomers--;
		}
		
	}

}
