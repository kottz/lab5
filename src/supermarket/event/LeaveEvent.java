package supermarket.event;
import sim.Event;
import sim.SortedSequence;
import supermarket.FIFO;
import supermarket.ShoppingState;
import supermarket.customer.Customer;
/**
 * Event som körs då en kund lämnar snabbköpet.
 *
 */
public class LeaveEvent extends Event {

	private Customer c;
<<<<<<< HEAD
=======
	private double time;
>>>>>>> dd07df44a7f06a9bd8bd49e5bec7bf63368d1fd6
	
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
