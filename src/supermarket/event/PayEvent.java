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
	
	public PayEvent(SortedSequence seq, ShoppingState state, Customer c, double time) {
		this.c = c;
		this.state = state;
		this.time = time;
	}
	
	public void execute(SortedSequence seq, ShoppingState state) {
	
		if(state.freeCheckout()) {
			state.idleCheckouts--;
			double newTime = time + state.calculateCheckoutTime();
			Event leave = new LeaveEvent(seq,state,c,newTime); //Skickar vidare denna kund till leave, newTime = tiden då betalningen är klar.
			seq.sortEventQueue(leave); //Lägger till kund och sorterar
		} else {
			q.add(c);
		}
	}
}
