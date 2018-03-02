package supermarket.event;
import sim.Event;
import sim.SortedSequence;
import supermarket.ShoppingState;
import supermarket.customer.Customer;
/**
 * Event som k�rs d� en kund l�mnar snabbk�pet.
 *
 */
public class LeaveEvent extends Event {

	private SortedSequence seq;
	private ShoppingState state;
	private double time;
	private Customer c;
	
	public LeaveEvent(SortedSequence seq, ShoppingState state, Customer c, double timeOfExecution) {
		this.seq = seq;
		this.state = state;
		this.c = c;
		time = timeOfExecution;
	}
	
	public void execute() {
		state.removeCustomer(c);
		state.completedCheckout();
		
		if(state.queue.isEmpty()) {
			state.idleCheckouts += 1;
		}else{
			seq.sortEventQueue(new LeaveEvent(seq, state, state.queue.first(), time + state.calculateCheckoutTime()));
			state.queue.removeFirst();
		}
	}
	
}
