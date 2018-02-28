package supermarket.event;
import sim.Event;
import supermarket.ShoppingState;
import sim.SimulatorStateADT;
import sim.SortedSequence;
/**
 * Event som k�rs d� en kund anl�nder.
 *
 */
public class ArrivalEvent extends Event {
	
	
	
	public ArrivalEvent(SortedSequence seq,  ShoppingState state) {
		
		time = state.calculateArrivalTime();
		
		
	}

	@Override
	public void execute(SortedSequence seq, ShoppingState state) {
		seq.sortEventQueue(new ArrivalEvent(seq,state));
		state.updateTotalQueueTime(time);
		
		if(state.isOpen() && state.getMaxCustomers() > state.getNumberOfCustomers()) {
			state.queue.add(state.factory.createCustomer());
			seq.sortEventQueue(new PayEvent());
		

		state.notifyObservers();
		state.hasChanged();
		}
		else {
			
			state.missedCustomers--;
		}
		
	}

}
