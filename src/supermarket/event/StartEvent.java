package supermarket.event;
import sim.Event;
import sim.SimStartEvent;
import supermarket.ShoppingState;
import sim.SimulatorState;
import sim.SortedSequence;
import supermarket.customer.CustomerSpawner;
/**
 * 
 *
 * 
 * @author Olof Bourghardt, August Brunns�ter, Oskar Havo , Edward K�llstedt
 *
 * Event som k�rs d� simuleringen startar.
 *
 */
public class StartEvent extends SimStartEvent {
	
	private ShoppingState state;
	
	/**
	 * 
	 * @param seq
	 * @param state
	 */
	public StartEvent(SortedSequence seq, ShoppingState state) {
		this.seq = seq;
		this.state = state;
		this.time=0.00d;
	}

	public String toString() {
		return "Start";
	}
	@Override
	public void execute() {
		//S�tter aktuella v�rden och uppdaterar.
		state.setCurrentEvent(this);
		state.update();
		
		state.start();
		state.openStore();
		
		//state.queue.add(state.factory.createCustomer());
		
		double nextArrivalTime = time + state.calculateArrivalTime();
		seq.sortEventQueue(new ArrivalEvent(seq,state,nextArrivalTime));
		state.notifyObservers();
		state.hasChanged();
		
		
		
	}

}
