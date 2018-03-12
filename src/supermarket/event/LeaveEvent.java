package supermarket.event;
import sim.Event;
import sim.SortedSequence;
import supermarket.ShoppingState;
import supermarket.customer.Customer;
/**
 *
 * 
 * @author Olof Bourghardt, August Brunnsäter, Oskar Havo , Edward Källstedt
 *
 * Event som körs då en kund lämnar snabbköpet.
 *
 */
public class LeaveEvent extends Event {

	private ShoppingState state;
	private Customer c;
	
	/**
	 * 
	 * @param seq
	 * @param state
	 * @param c
	 * @param timeOfExecution
	 */
	public LeaveEvent(SortedSequence seq, ShoppingState state, Customer c, double timeOfExecution) {
		this.seq = seq;
		this.state = state;
		this.c = c;
		
		time = timeOfExecution;
	}
	
	/**
	 * Returnerar tiden då eventet skall exekveras
	 */

	
	/**
	 * Returnerar namnet på eventet
	 */
	public String toString() {
		return "Betal";
	}

	/**
	 * Kör eventet
	 */
	public void execute() {
		//Sätter aktuella värden och uppdaterar.
		state.setIdleCheckoutTime(time);
		state.setTotalQueueTime(time);
		state.setLastCheckoutTime(time);
		state.setCurrentEvent(this);
		state.setCurrentCustomer(c);
		state.update();
		
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
