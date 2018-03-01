package supermarket.event;
import sim.Event;
import sim.SortedSequence;
import supermarket.ShoppingState;
import supermarket.customer.Customer;
import random.*;
import supermarket.FIFO;

/**
 * Event som körs då en kund står i kassan och betalar.
 *
 */
public class PayEvent extends Event {
	
	private Customer c;
	private ShoppingState state;
	private FIFO q = state.queue;
	
	public void execute(SortedSequence seq, ShoppingState state) {
		
	}
	
	public PayEvent(SortedSequence seq, ShoppingState state, Customer c, double time) {
		this.c = c;
		this.state = state;
		this.time = time;
	}

}
