package supermarket.event;
import sim.Event;
import supermarket.ShoppingState;
import sim.SimulatorStateADT;
import sim.SortedSequence;
import supermarket.customer.CustomerSpawner;
/**
 * Event som körs då simuleringen startar.
 *
 */
public class StartEvent extends Event {
	
	private double time;
	private SortedSequence seq;
	private ShoppingState state;
	
	public StartEvent(SortedSequence seq, ShoppingState state) {
		this.seq = seq;
		this.state = state;
		this.time=0.00d;
	}

	@Override
	public void execute() {
		state.setCurrentTime(time);
		state.openStore();
		
		state.queue.add(state.factory.createCustomer());	
		double nextArrivalTime = time + state.calculateArrivalTime();
		seq.sortEventQueue(new ArrivalEvent(seq,state,nextArrivalTime));
		state.notifyObservers();
		state.hasChanged();
		
		
		
	}

}
