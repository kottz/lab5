package supermarket.event;
import sim.Event;
import sim.SortedSequence;
import supermarket.ShoppingState;
import supermarket.customer.Customer;
/**
 * Event som körs då en kund står i kassan och betalar.
 *
 */
public class PayEvent extends Event {
	
	private Customer c;
	
	public PayEvent(SortedSequence seq, ShoppingState state, Customer c) {
		this.c = c;
		time = state.calculateCheckoutTime();
	}
	
	public void execute(SortedSequence seq, ShoppingState state) {
		state.removeCustomer(c);
		state.completedCheckout();
		
		if(state.queue.isEmpty()) {
			state.idleCheckouts += 1;
		}else{
			seq.sortEventQueue(new PayEvent(seq, state, state.queue.first()));
			state.queue.removeFirst();
		}
	}

}
