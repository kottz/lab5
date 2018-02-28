package supermarket.event;
import sim.Event;
import supermarket.ShoppingState;
import sim.SimulatorStateADT;
import sim.SortedSequence;
/**
 * Event som k�rs d� simuleringen avslutas.
 *
 */
public class StopEvent extends Event {

	@Override
	public void execute(SortedSequence seq, ShoppingState state) {
		
//		state.n�dbroms = true;
	//	System.exit(0);
		seq.sortEventQueue(this);
		state.notifyObservers();
		state.hasChanged();
		
		
		
	}

}
