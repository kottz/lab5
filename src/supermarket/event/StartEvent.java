package supermarket.event;
import sim.Event;
import supermarket.ShoppingState;
import sim.SimulatorStateADT;
import sim.SortedSequence;
import supermarket.customer.CustomerSpawner;
/**
 * 
 *
 * 
 * @author Olof Bourghardt, August Brunnsäter, Oskar Havo , Edward Källstedt
 *
 * Event som körs då simuleringen startar.
 *
 */
public class StartEvent extends Event {
	
	private double time;
	private SortedSequence seq;
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

	public double getTime() {
		return time;
	}
	public String toString() {
		return "Start";
	}
	@Override
	public void execute() {
		//Sätter aktuella värden och uppdaterar.
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
