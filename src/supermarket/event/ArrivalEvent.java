package supermarket.event;
import sim.Event;
import supermarket.ShoppingState;
import supermarket.customer.Customer;
import sim.SortedSequence;
/**
 * Event som körs då en kund anländer.
 *
 */
public class ArrivalEvent extends Event {
	
	private double time;
	private SortedSequence seq;
	private ShoppingState state;
	private Customer c;
	
	public ArrivalEvent(SortedSequence seq,  ShoppingState state, double time) {
		this.time = time;
		this.seq = seq;
		this.state = state;
	}
	public double getTime() {
		return time;
	}
	public String toString() {
		return "Ankomst";
	}
	@Override
	public void execute() {
		if(state.isOpen() && state.getMaxCustomers() > state.getNumberOfCustomers()) {
			c = state.factory.createCustomer();
			state.setCurrentCustomer(c);
		}
		//Sätter aktuella värden och uppdaterar.
		state.setCurrentEvent(this);
		
		state.update();
		
		
		if(state.isOpen()) {
		double nextArrivalTime = time + state.calculateArrivalTime();
		seq.sortEventQueue(new ArrivalEvent(seq,state,nextArrivalTime));
		state.updateTotalQueueTime(time);
		}
		
		if(state.isOpen() && state.getMaxCustomers() > state.getNumberOfCustomers()) {
			//c = state.factory.createCustomer();
			state.addCustomer(c);
			double nextPayTime = time + state.calculateShoppingTime();
			seq.sortEventQueue(new PayEvent(seq, state,c, nextPayTime));
		

		//state.notifyObservers();
		//state.hasChanged();
		}
		else if (state.isOpen()){
			
			state.missedCustomers++;
		}
		
	}

}
