package supermarket.event;
import sim.Event;
import sim.SortedSequence;
import supermarket.ShoppingState;
import supermarket.customer.Customer;
/**
 *
 * 
 * @author Olof Bourghardt, August Brunns�ter, Oskar Havo , Edward K�llstedt
 *
 * Event som k�rs d� en kund l�mnar snabbk�pet.
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
	 * Returnerar tiden d� eventet skall exekveras
	 */

	
	/**
	 * Returnerar namnet p� eventet
	 */
	public String toString() {
		return "Betal";
	}

	/**
	 * K�r eventet
	 */
	public void execute() {
		//S�tter aktuella v�rden och uppdaterar.
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
