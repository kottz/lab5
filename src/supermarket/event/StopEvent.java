package supermarket.event;
import sim.Event;
import supermarket.ShoppingState;
import sim.SimulatorStateADT;
import sim.SortedSequence;
/**
 * Event som körs då simuleringen avslutas.
 *
 */
public class StopEvent extends Event {
	
	private double time;
	private SortedSequence seq;
	private ShoppingState state;
	
	public StopEvent(SortedSequence seq, ShoppingState state, double time) {
		this.seq = seq;
		this.state = state;
		this.time = time;
	}

	@Override
	public void execute() {
		state.setCurrentTime(time);

		state.stop();
		state.updateTotalQueueTime(this.time);
		state.notifyObservers();
		state.hasChanged();
		
		
		
	}

}
