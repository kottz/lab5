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
	
	private double time;
	private SortedSequence seq;
	private ShoppingState state;
	private Customer c;
	private FIFO q;
	
	public PayEvent(SortedSequence seq, ShoppingState state, Customer c, double time) {
		this.seq = seq;
		this.state = state;
		this.time = time;
		this.c = c;
		this.q = state.queue;
	}
	
	public void execute() {
		state.setCurrentTime(time);
	
		if(state.freeCheckout()) {
			state.idleCheckouts--;
			double nextLeaveTime = time + state.calculateCheckoutTime();
			Event leave = new LeaveEvent(seq,state,c,nextLeaveTime); //Skickar vidare denna kund till leave, newTime = tiden då betalningen är klar.
			seq.sortEventQueue(leave); //Lägger till kund och sorterar
		} else {
			q.add(c);
		}
	}
}
