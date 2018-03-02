package supermarket.event;
import sim.Event;
import sim.SortedSequence;
import supermarket.FIFO;
import supermarket.ShoppingState;
import supermarket.customer.Customer;
/**
 * Event som k�rs d� en kund l�mnar snabbk�pet.
 *
 */
public class LeaveEvent extends Event {

	private Customer c;
	
	public LeaveEvent(SortedSequence seq, ShoppingState state, Customer c, double timeOfExecution) {
		this.c = c;
		
		time = timeOfExecution;
	}
	
	public void execute(SortedSequence seq, ShoppingState state) {
		state.removeCustomer(c);
		state.completedCheckout();
		
		if(state.queue.isEmpty()) {
			state.idleCheckouts += 1;
		}else{
			seq.sortEventQueue(new LeaveEvent(seq, state, state.queue.first(), time + state.calculateCheckoutTime()));
			state.queue.removeFirst();
		}
		
		state.update();
	}
	
}
