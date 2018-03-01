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
	
	public StopEvent() {
		
		//this.time = ?;
	}

	@Override
	public void execute(SortedSequence seq, ShoppingState state) {

		state.stop();
		state.updateTotalQueueTime(this.time);
		state.notifyObservers();
		state.hasChanged();
		
		
		
	}

}
