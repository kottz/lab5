package sim;

import supermarket.ShoppingState;

/**
 * 
 * @author Oskar
 *
 */
public abstract class Event {

	// Tidsvariabel
	public double time;

	// Utför något med hjälp av sekvensen och ett state.
	public abstract void execute(SortedSequence seq, ShoppingState state);
}
