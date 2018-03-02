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
	private SortedSequence seq;
	private ShoppingState state;
	
	public ArrivalEvent(SortedSequence seq,  ShoppingState state, double time) {
		this.time = time;
		this.seq = seq;
		this.state = state;
	}

	@Override
	public void execute() {
		state.setCurrentTime(time);
		
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
