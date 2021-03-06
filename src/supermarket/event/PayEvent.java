package supermarket.event;
import sim.Event;
import sim.SortedSequence;
import supermarket.ShoppingState;
import supermarket.customer.Customer;
import random.*;
import supermarket.FIFO;

/**
 * 
 * 
 * @author Olof Bourghardt, August Brunns�ter, Oskar Havo , Edward K�llstedt
 *
 * Event som k�rs d� en kund st�r i kassan och betalar.
 *
 */
public class PayEvent extends Event {
	
	private ShoppingState state;
	private Customer c;
	private FIFO q;
	
	/**
	 * 
	 * @param seq
	 * @param state
	 * @param c
	 * @param time
	 */
	public PayEvent(SortedSequence seq, ShoppingState state, Customer c, double time) {
		this.seq = seq;
		this.state = state;
		this.time = time;
		this.c = c;
		this.q = state.queue;
	}

	public String toString() {
		return "Plock";
	}
	public void execute() {
		//S�tter aktuella v�rden och uppdaterar.
		state.setIdleCheckoutTime(time);
		state.setTotalQueueTime(time);
		state.setCurrentEvent(this);
		state.setCurrentCustomer(c);
		state.update();
	
		if(state.freeCheckout()) {
			state.idleCheckouts--;
			double nextLeaveTime = time + state.calculateCheckoutTime();
			Event leave = new LeaveEvent(seq,state,c,nextLeaveTime); //Skickar vidare denna kund till leave, newTime = tiden d� betalningen �r klar.
			seq.sortEventQueue(leave); //L�gger till kund och sorterar
		} else {
			q.add(c);
		}
	}
}
