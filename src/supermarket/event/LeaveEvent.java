package supermarket.event;
import sim.Event;
import sim.SortedSequence;
import supermarket.ShoppingState;
import supermarket.customer.Customer;
/**
 * Event som körs då en kund lämnar snabbköpet.
 *
 */
public class LeaveEvent extends Event {

private Customer c;
	
	public LeaveEvent(SortedSequence seq, ShoppingState state, Customer c) {
		this.c = c;
		time = state.calculateCheckoutTime();
	}
	
	public void execute(SortedSequence seq, ShoppingState state) {
		state.removeCustomer(c);
		state.completedCheckout();
		
		if(state.queue.isEmpty()) {
			state.idleCheckouts += 1;
		}else{
			seq.sortEventQueue(new LeaveEvent(seq, state, state.queue.first()));
			state.queue.removeFirst();
		}
	}
	
}
