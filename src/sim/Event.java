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

	// Utf�r n�got med hj�lp av sekvensen och ett state.
	public abstract void execute(SortedSequence seq, ShoppingState state);
}
