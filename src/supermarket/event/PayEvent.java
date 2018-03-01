package supermarket.event;
import sim.Event;
import sim.SortedSequence;
import supermarket.ShoppingState;
import supermarket.customer.Customer;
import random.*;
import supermarket.FIFO;

/**
 * Event som k�rs d� en kund st�r i kassan och betalar.
 *
 */
public class PayEvent extends Event {
	
	private Customer c;
	private ShoppingState state;
	private FIFO q = state.queue;
	
	public PayEvent(SortedSequence seq, ShoppingState state, Customer c, double time) {
		this.c = c;
		this.state = state;
		this.time = time;
	}
	
	public void execute(SortedSequence seq, ShoppingState state) {
	
		if(state.freeCheckout()) {
			state.idleCheckouts--;
			double newTime = time + state.calculateCheckoutTime();
			Event leave = new LeaveEvent(seq,state,c,newTime); //Skickar vidare denna kund till leave, newTime = tiden d� betalningen �r klar.
			seq.sortEventQueue(leave); //L�gger till kund och sorterar
		} else {
			q.add(c);
		}
	}
}
